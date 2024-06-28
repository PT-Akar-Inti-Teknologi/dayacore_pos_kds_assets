package app.dayacore.core.rabbitmq

import app.dayacore.core.utils.Empty
import co.touchlab.kermit.Logger
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Consumer
import com.rabbitmq.client.Envelope
import com.rabbitmq.client.ShutdownSignalException
import java.nio.charset.StandardCharsets

object AppRabbitMQ {

    private lateinit var factory: ConnectionFactory
    private lateinit var connection: Connection

    data class ConnectionCallback(
        val onConnected: (String) -> Unit,
        val onDisconnected: (String) -> Unit,
        val onSuccessRecover: (String) -> Unit,
        val onReceived: (String, String) -> Unit,
    )

    fun initConnection(
        usernameValue: String,
        passwordValue: String,
        hostnameValue: String,
        portValue: Int,
    ) {
        factory = ConnectionFactory()
            .apply {
                username = usernameValue
                password = passwordValue
                host = hostnameValue
                virtualHost = ConnectionFactory.DEFAULT_VHOST
                port = portValue
            }
        factory.setSslContextFactory(null)
        connection = factory.newConnection()
    }

    fun consume(
        queueValue: String,
        exchange: String = "amq.fanout",
        exchangeType: String = "fanout",
        routingKey: String = String.Empty,
        callback: ConnectionCallback,
    ) {
        val channel = connection.createChannel().apply {
            exchangeDeclare(exchange, exchangeType, true)
            queueDeclare(queueValue, false, false, false, null)
            queueBind(queueValue, exchange, routingKey)
        }

        channel.basicConsume(queueValue, false, object : Consumer {
            override fun handleConsumeOk(consumerTag: String?) {
                Logger.i { "$consumerTag has been registered as a callback" }
                if (consumerTag != null)
                    callback.onConnected.invoke(consumerTag)
            }

            override fun handleCancelOk(consumerTag: String?) {
                // Perform cancellation tasks such as closing resources here
                Logger.i { "AppRabbitMQConsumer-handleCancelOk : $consumerTag" }
            }

            override fun handleCancel(consumerTag: String?) {
                // Perform cancellation tasks such as closing resources here
                Logger.i { "AppRabbitMQConsumer-handleCancel : $consumerTag" }
            }

            override fun handleShutdownSignal(consumerTag: String?, sig: ShutdownSignalException?) {
                if (sig != null)
                    callback.onDisconnected.invoke(sig.message.orEmpty())
            }

            override fun handleRecoverOk(consumerTag: String?) {
                // If connection issues, try to receive messages again
                Logger.i { "AppRabbitMQConsumer-handleRecoverOk : $consumerTag" }
                if (consumerTag != null)
                    callback.onSuccessRecover.invoke(consumerTag)
            }

            override fun handleDelivery(
                consumerTag: String?,
                envelope: Envelope?,
                properties: AMQP.BasicProperties?,
                body: ByteArray?,
            ) {
                if (consumerTag != null && body != null)
                    callback.onReceived.invoke(consumerTag, body.decodeToString())

                envelope?.deliveryTag?.let { channel.basicAck(it, false) }
            }
        })
    }

    fun publish(
        queue: String,
        exchange: String = "amq.fanout",
        exchangeType: String = "fanout",
        message: String,
    ) {
        val channel = connection.createChannel().apply {
            exchangeDeclare(exchange, exchangeType, true)
        }

        channel.basicPublish(
            exchange,
            queue,
            null,
            message.toByteArray(StandardCharsets.UTF_8)
        )
    }

    fun close() {
        if (connection.isOpen)
            connection.close()
    }
}