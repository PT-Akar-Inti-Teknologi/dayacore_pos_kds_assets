package com.ait.customkeyboard.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ait.customkeyboard.keyboard.KeyboardView
import com.ait.customkeyboard.model.Key

@Composable
fun CustomKeyboardContent(
    modifier: Modifier,
    textFieldState: MutableState<TextFieldValue>?,
    isVisible: Boolean,
    onAction: (Key) -> Unit = {},
    onKeyPress: (Key) -> Unit = {},
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val boxWithConstraintsScope = this
        // You can use this scope to get the minWidth, maxWidth, minHeight, maxHeight in dp and constraints
        val keyboardWidth = boxWithConstraintsScope.maxWidth.div(2)
        val keyboardHeight = keyboardWidth.div(10).times(4.3f)

        content.invoke()

        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .widthIn(max = keyboardWidth)
                .heightIn(max = keyboardHeight)
                .align(alignment = Alignment.BottomCenter),
            enter = expandVertically() + slideInVertically(),
            exit = shrinkVertically() + slideOutVertically()
        ) {
            KeyboardView(
                modifier = Modifier
                    .widthIn(max = keyboardWidth)
                    .heightIn(max = keyboardHeight)
                    .shadow(8.dp),
                textFieldState = textFieldState,
                focusFirstKey = true,
                enableEmailSuggestions = false,
                onAction = onAction,
                onKeyPress = onKeyPress
            )
        }
    }
}