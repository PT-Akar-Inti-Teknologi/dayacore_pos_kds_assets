package app.dayacore.core.presentation

import app.dayacore.core.utils.fromJson
import app.dayacore.core.utils.isValidIPv4
import app.dayacore.core.utils.toJson
import co.touchlab.kermit.Logger
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.ServerSocket
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.network.sockets.toJavaAddress
import io.ktor.util.network.address
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.close
import io.ktor.utils.io.readUTF8Line
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

object KtorSocket {

    // get from terminal execute: "ifconfig" -> IP: en0

    private lateinit var serverSocket: ServerSocket
    private lateinit var clientSocket: Socket

    private lateinit var clientWriteChannel: ByteWriteChannel

    // used to pass data between server and client
    @Serializable
    data class Data(
        val command: String, // from object DataCommand
        val data: String // Json String
    )

    suspend fun initServer(
        hostName: String,
        port: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        connectionStatusCallback: (Boolean) -> Unit,
        receivedCallback: (Data, ByteWriteChannel) -> Unit
    ) {
        val selectorManager = SelectorManager(dispatcher = dispatcher)
        val socketTcp = aSocket(selectorManager).tcp()
        serverSocket = socketTcp.bind(hostName, port)

        Logger.i { "Server is listening at ${serverSocket.localAddress}" }
        Logger.i { "Server is listening at - java address ${serverSocket.localAddress.toJavaAddress().address}" }
        // callback connection status
        connectionStatusCallback.invoke(serverSocket.localAddress.toJavaAddress().address.isValidIPv4())

        withContext(dispatcher) {
            while (true) {
                val socket = serverSocket.accept()
                launch {
                    val receiveChannel: ByteReadChannel = socket.openReadChannel()
                    val writeChannel: ByteWriteChannel = socket.openWriteChannel(autoFlush = true)
                    if (socket.isActive)
                        Logger.i { "Accepted Client from ${socket.remoteAddress}" }

                    while (socket.isActive) {
                        val dataString = receiveChannel.readUTF8Line()
                        Logger.i { "initServer-dataString : $dataString" }
                        if (!dataString.isNullOrBlank()) {
                            val data = dataString.fromJson(deserializer = Data.serializer())
                            receivedCallback.invoke(data, writeChannel)
                        } else {
                            break
                        }
                    }
                }
            }
        }
    }

    suspend fun initClient(
        hostName: String,
        port: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        connectionStatusCallback: (Boolean) -> Unit,
        callback: (Data) -> Unit
    ) {
        val selectorManager = SelectorManager(dispatcher = dispatcher)
        val socketTcp = aSocket(selectorManager).tcp()
        clientSocket = socketTcp.connect(hostName, port)

        val receiveChannel = clientSocket.openReadChannel()
        clientWriteChannel = clientSocket.openWriteChannel(autoFlush = true)

        // connection status callback
        connectionStatusCallback.invoke(KtorSocket::clientWriteChannel.isInitialized)

        withContext(dispatcher) {
            launch {
                while (clientSocket.isActive) {
                    val dataString = receiveChannel.readUTF8Line()
                    if (!dataString.isNullOrBlank()) {
                        val data = dataString.fromJson(deserializer = Data.serializer())
                        callback(data)
                    } else {
                        Logger.i { "Server closed a connection" }
                        clientSocket.close()
                        selectorManager.close()
                        clientWriteChannel.close()

                        // connection status callback
                        connectionStatusCallback.invoke(false)
                    }
                }
            }
        }
    }

    // use RequestCommand reference for argument
    suspend fun clientSendCommand(command: String) {
        if (KtorSocket::clientWriteChannel.isInitialized) {
            try {
                val dataToSend = Data(command = command, data = "")
                val dataJson = dataToSend.toJson(serializer = Data.serializer())
                Logger.i { "clientSendCommand: $dataJson" }
                clientWriteChannel.writeStringUtf8(s = "$dataJson\n")
            } catch (e: Exception) {
                Logger.e { "clientSendCommand-Exception : $e" }
            }
        }
    }

    // use RequestCommand reference for argument
    suspend fun clientSendData(data: Data) {
        if (KtorSocket::clientWriteChannel.isInitialized) {
            try {
                val dataJson = data.toJson(serializer = Data.serializer())
                Logger.i { "clientSendData: $dataJson" }
                clientWriteChannel.writeStringUtf8(s = "$dataJson\n")
            } catch (e: Exception) {
                Logger.i { "clientSendData-Exception : $e" }
            }
        }
    }

    suspend fun clientSendString(jsonString: String) {
        if (KtorSocket::clientWriteChannel.isInitialized) {
            try {
                Logger.i { "clientSendString: $jsonString" }
                clientWriteChannel.writeStringUtf8(s = "$jsonString\n")
            } catch (e: Exception) {
                Logger.i { "clientSendString-Exception : $e" }
            }
        }
    }

    fun serverClose() {
        if (KtorSocket::serverSocket.isInitialized)
            serverSocket.close()
    }

    fun clientClose() {
        if (KtorSocket::clientSocket.isInitialized)
            clientSocket.close()
    }
}