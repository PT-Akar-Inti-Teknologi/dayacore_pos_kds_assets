package com.ait.customkeyboard.utilities

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

fun TextFieldValue.removeLastCharOrEmpty(selectionIndex: Int): TextFieldValue {
    return if (selectionIndex < this.text.length) {
        val subStringBeforeSelect = this.text.substring(
            startIndex = 0,
            endIndex = selectionIndex
        )
        val subStringAfterSelect = this.text.substring(
            startIndex = selectionIndex,
            endIndex = this.text.length
        )
        val length = subStringBeforeSelect.length - 1
        TextFieldValue(
            text = (if (length >= 0) text.take(length) else "").plus(other = subStringAfterSelect),
            TextRange(index = subStringBeforeSelect.length)
        )
    } else {
        val length = text.length - 1
        TextFieldValue(
            text = if (length >= 0) text.take(length) else "",
            TextRange(index = text.length)
        )
    }
}

fun MutableState<TextFieldValue>.updateWith(text: String?, selectionIndex: Int = 0) {
    value = TextFieldValue(text = text ?: return, TextRange(index = selectionIndex))
}

fun MutableState<Boolean>.toggle() {
    value = !value
}

fun MutableState<TextFieldValue>.clear() {
    updateWith(text = "")
}

fun MutableState<TextFieldValue>.updateAndRemoveLastChar(selectionIndex: Int) {
    updateWith(
        value = value.removeLastCharOrEmpty(selectionIndex = selectionIndex),
        selectionIndex = selectionIndex.minus(other = 1).takeIf { it >= 0 } ?: 0
    )
}

fun MutableState<TextFieldValue>.updateWith(value: TextFieldValue?, selectionIndex: Int) {
    updateWith(text = value?.text, selectionIndex = selectionIndex)
}

fun MutableState<TextFieldValue>.append(newText: String?, selectionIndex: Int) {
    if (selectionIndex < value.text.length) {
        val subStringBeforeSelect = value.text.substring(
            startIndex = 0,
            endIndex = selectionIndex
        )
        val subStringAfterSelect = value.text.substring(
            startIndex = selectionIndex,
            endIndex = value.text.length
        )
        updateWith(
            text = subStringBeforeSelect.plus(other = newText).plus(other = subStringAfterSelect),
            selectionIndex = selectionIndex.plus(other = newText?.length ?: 0)
        )
    } else {
        updateWith(
            text = this.value.text.plus(other = newText),
            selectionIndex = selectionIndex.plus(other = newText?.length ?: 0)
        )
    }
}