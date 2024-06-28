package com.ait.customkeyboard.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ait.customkeyboard.data.KeysDataSource
import com.ait.customkeyboard.model.Key
import com.ait.customkeyboard.utilities.append
import com.ait.customkeyboard.utilities.clear
import com.ait.customkeyboard.utilities.handleCaseMode
import com.ait.customkeyboard.utilities.isAbc
import com.ait.customkeyboard.utilities.isAction
import com.ait.customkeyboard.utilities.isBackspace
import com.ait.customkeyboard.utilities.isClear
import com.ait.customkeyboard.utilities.isNumeric
import com.ait.customkeyboard.utilities.isSpecialCharacters
import com.ait.customkeyboard.utilities.isToggleKey
import com.ait.customkeyboard.utilities.isUppercase
import com.ait.customkeyboard.utilities.toggle
import com.ait.customkeyboard.utilities.updateAndRemoveLastChar

@Composable
fun KeyboardView(
    modifier: Modifier = Modifier,
    textFieldState: MutableState<TextFieldValue>?,
    focusFirstKey: Boolean = false,
    enableEmailSuggestions: Boolean = true,
    onAction: ((key: Key) -> Unit)? = null,
    onKeyPress: (key: Key) -> Unit,
) {
    val focusKey = remember { mutableStateOf(focusFirstKey) }
    val isUppercase = remember { mutableStateOf(false) }
    val isNumeric = remember { mutableStateOf(false) }
    val isSpecialCharacters = remember { mutableStateOf(false) }
    val alphabets = remember { mutableStateOf(KeysDataSource.normalKeys) }
    val numericKeys = remember { mutableStateOf(KeysDataSource.numericKeys) }
    val specialCharactersKeys = remember { mutableStateOf(KeysDataSource.specialCharactersKeys) }

    val keys by rememberUpdatedState(
        if (isNumeric.value) {
            numericKeys.value
        } else if (isSpecialCharacters.value) {
            specialCharactersKeys.value
        } else {
            alphabets.value
        }
    )
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium
            )
            .padding(8.dp)
    ) {
        if (enableEmailSuggestions)
            EmailSuggestionsRow(textFieldState) {}

        LazyVerticalGrid(
            columns = GridCells.Fixed(10)
        ) {
            items(keys.size,
                key = { index ->
                    keys[index].text
                },
                span = { index ->
                    GridItemSpan(keys[index].span)
                }) { index ->
                KeyboardButton(
                    key = keys[index],
                    requestFocus = focusKey.value && index == 0,
                    isUppercaseEnable = isUppercase.value,
                    isToggle = keys[index].isToggleKey(),
                ) {
                    when {
                        it.isUppercase() -> {
                            isUppercase.toggle()
                        }

                        it.isAction() -> {
                            onAction?.invoke(it)
                        }

                        it.isSpecialCharacters() -> {
                            isSpecialCharacters.toggle()
                            isNumeric.value = false
                        }

                        it.isNumeric() || it.isAbc() -> {
                            isNumeric.toggle()
                            isSpecialCharacters.value = false
                        }

                        else -> {
                            onKeyPress(it)
                            processKeys(it, textFieldState, isUppercase.value)
                        }
                    }
                }
            }
        }
    }
}

fun processKeys(it: Key, state: MutableState<TextFieldValue>?, isUppercase: Boolean) {
    if (it.isBackspace()) {
        state?.updateAndRemoveLastChar(selectionIndex = state.value.selection.start)
    } else if (it.isClear()) {
        state?.clear()
    } else {
        state?.append(
            newText = it.handleCaseMode(isUppercase),
            selectionIndex = state.value.selection.start
        )
    }
}