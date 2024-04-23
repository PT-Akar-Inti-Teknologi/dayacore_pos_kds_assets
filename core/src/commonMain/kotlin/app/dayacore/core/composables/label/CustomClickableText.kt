package app.dayacore.core.composables.label

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomClickableText(
    modifier: Modifier,
    firstText: String = "",
    clickableText: String,
    lastText: String = "",
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit,
) {
    val tag = "CLICK"
    val annotatedString = buildAnnotatedString {
        // Add regular text
        if (firstText.isNotEmpty())
            append(text = firstText.plus(" "))

        // Add clickable text with a link to www.example.com
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        ) {
            append(text = clickableText)
            // Add a string annotation with the URL
            addStringAnnotation(
                tag = tag,
                annotation = clickableText,
                start = firstText.length,
                end = firstText.length + clickableText.length
            )
        }

        // Add more regular text
        if (lastText.isNotEmpty())
            append(text = lastText)
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            // Check if the clicked text has a URL annotation
            val annotations = annotatedString.getStringAnnotations(tag, offset, offset)
            if (annotations.isNotEmpty()) {
                onClick.invoke()
            }
        },
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = fontWeight,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = textAlign
        )
    )
}