package app.dayacore.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.dayacore.core.composables.label.CustomText
import app.dayacore.core.utils.Empty

@Composable
fun CustomViewWrapper(
    modifier: Modifier = Modifier.fillMaxSize(),
    contentBackground: (@Composable (BoxScope) -> Unit)? = null,
    vBodyArrangement: Arrangement.Vertical = Arrangement.Top,
    hBodyAlignment: Alignment.Horizontal = Alignment.Start,
    contentBody: @Composable (ColumnScope) -> Unit,
    contentBottom: (@Composable (ColumnScope) -> Unit)? = null,
    toastMessage: String = String.Empty,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        /* content background */
        Box(modifier = Modifier.fillMaxSize()) {
            contentBackground?.invoke(this)
        }
        /* body and bottom */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            /* content body */
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = vBodyArrangement,
                horizontalAlignment = hBodyAlignment,
            ) {
                contentBody.invoke(this)
            }
            /* content bottom */
            contentBottom?.invoke(this)
        }
        /* toast message */
        if (toastMessage.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    text = toastMessage,
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}