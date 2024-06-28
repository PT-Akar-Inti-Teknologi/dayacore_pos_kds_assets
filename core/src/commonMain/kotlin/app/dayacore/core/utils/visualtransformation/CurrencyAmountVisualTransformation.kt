package app.dayacore.core.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import app.dayacore.core.extensions.formatCurrency

class CurrencyAmountVisualTransformation(
    private val currencyCode: String = "Rp",
) : VisualTransformation {

    private fun String.convertToCurrency(): String =
        if (this.isNotBlank()) {
            val currencyFormat = this.formatCurrency()
            "$currencyCode$currencyFormat"
        } else {
            this
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = originalText.convertToCurrency()

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                if (originalText.isNotBlank()) {
                    return when {
                        offset <= 3 -> offset + currencyCode.length
                        offset <= 6 -> offset + currencyCode.length + 1
                        offset <= 9 -> offset + currencyCode.length + 2
                        offset <= 12 -> offset + currencyCode.length + 3
                        offset <= 14 -> offset + currencyCode.length + 4
                        else -> 21
                    }
                }
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (originalText.isNotBlank()) {
                    return when {
                        offset <= 3 -> 0
                        offset <= 6 -> offset - (currencyCode.length + 1)
                        offset <= 10 -> offset - (currencyCode.length + 2)
                        offset <= 14 -> offset - (currencyCode.length + 3)
                        offset <= 18 -> offset - (currencyCode.length + 4)
                        offset <= 22 -> offset - (currencyCode.length + 5)
                        else -> offset - 2
                    }
                }
                return offset
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping
        )
    }
}