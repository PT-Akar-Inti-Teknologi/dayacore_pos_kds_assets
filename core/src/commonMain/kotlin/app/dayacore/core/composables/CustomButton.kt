package app.dayacore.core.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CustomButtonParams(
    val text: String,
    val enabled: Boolean = false,
    val colorEnable: Pair<Color?, Color> = Pair(null, Color.White),
    val colorDisable: Pair<Color?, Color?> = Pair(null, null),
    val borderShape: Pair<BorderStroke?, Shape> = Pair(
        null,
        RoundedCornerShape(8.dp)
    ),
    /* border , shape*/
    val contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    val buttonIcon: Painter? = null,
)

@Composable
fun CustomButton(
    customButtonParams: CustomButtonParams,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Button(
        onClick = { action.invoke() },
        modifier = modifier.then(
            Modifier
                .height(56.dp)
        ),
        shape = customButtonParams.borderShape.second,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = customButtonParams.colorEnable.first
                ?: MaterialTheme.colorScheme.primary,
            disabledContainerColor = customButtonParams.colorDisable.first
                ?: MaterialTheme.colorScheme.outline
        ),
        border = customButtonParams.borderShape.first,
        enabled = customButtonParams.enabled,
        contentPadding = customButtonParams.contentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (customButtonParams.buttonIcon != null)
                Icon(
                    painter = customButtonParams.buttonIcon,
                    contentDescription = "painterResourceIconButton",
                    modifier = Modifier.padding(end = 6.dp)
                )

            Text(
                text = customButtonParams.text,
                style = TextStyle(
//                    fontFamily = fontFamilyResource(sharedFont.bld),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp,
                    fontFeatureSettings = "lnum",
                ),
                color = if (customButtonParams.enabled) {
                    customButtonParams.colorEnable.second
                } else {
                    customButtonParams.colorDisable.second ?: Color.White
                }
            )
        }
    }
}

@Composable
fun CustomContentButton(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    backgroundColor: Color,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun CustomContentOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    backgroundColor: Color,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        contentPadding = contentPadding,
        content = content
    )
}