package com.ait.customkeyboard.utilities

import com.ait.customkeyboard.data.KeysDataSource
import com.ait.customkeyboard.model.Key
import com.ait.customkeyboard.model.NumericUtilityKey
import com.ait.customkeyboard.model.TextUtilityKey
import com.ait.customkeyboard.model.UtilityKey

fun Key.isBackspace() = this is UtilityKey.Backspace || this is NumericUtilityKey.Backspace
fun Key.isUppercase() = this is UtilityKey.Uppercase
fun Key.isToggleKey() = KeysDataSource.toggleKeys.contains(this)
fun Key.isAction() = this is UtilityKey.ActionArrow
fun Key.isNumeric() = this is TextUtilityKey.Numeric
fun Key.isAbc() = this is TextUtilityKey.ABC
fun Key.isClear() = this is UtilityKey.Clear
fun Key.isSpecialCharacters() = this is TextUtilityKey.SpecialCharacters
fun Key.handleCaseMode(isUppercaseEnabled: Boolean) =
    if (isUppercaseEnabled)
        text.uppercase()
    else
        text.lowercase()