package app.dayacore.core.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun phoneVisualTransformation(text: AnnotatedString): TransformedText {
    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset < 5 -> offset
                offset in 5 until 9 -> offset + 3
                offset in 9 until 13 -> offset + 6
                offset in 13 until 17 -> offset + 9
                offset in 17 until 21 -> offset + 12
                offset in 21 until 25 -> offset + 15
                offset in 25 until 29 -> offset + 18
                offset in 29 until 33 -> offset + 21
                else -> 24
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset < 5 -> offset
                offset in 5 until 12 -> offset - 3
                offset in 12 until 19 -> offset - 6
                offset in 19 until 26 -> offset - 9
                else -> offset
            }
        }
    }
    val trimmed = text.text.filter { it.isDigit() }
    val builder = AnnotatedString.Builder(trimmed.length + 2 * (trimmed.length / 4))
    for (i in trimmed.indices) {
        builder.append(trimmed[i])
        if ((i + 1) % 4 == 0 && i != trimmed.lastIndex) {
            builder.append(" - ")
        }
    }
    return TransformedText(builder.toAnnotatedString(), phoneNumberOffsetTranslator)
}