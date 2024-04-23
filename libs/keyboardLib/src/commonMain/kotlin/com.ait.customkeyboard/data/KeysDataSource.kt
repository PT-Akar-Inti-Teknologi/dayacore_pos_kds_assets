package com.ait.customkeyboard.data

import com.ait.customkeyboard.model.Alphabets.A
import com.ait.customkeyboard.model.Alphabets.B
import com.ait.customkeyboard.model.Alphabets.C
import com.ait.customkeyboard.model.Alphabets.D
import com.ait.customkeyboard.model.Alphabets.E
import com.ait.customkeyboard.model.Alphabets.F
import com.ait.customkeyboard.model.Alphabets.G
import com.ait.customkeyboard.model.Alphabets.H
import com.ait.customkeyboard.model.Alphabets.I
import com.ait.customkeyboard.model.Alphabets.J
import com.ait.customkeyboard.model.Alphabets.K
import com.ait.customkeyboard.model.Alphabets.L
import com.ait.customkeyboard.model.Alphabets.M
import com.ait.customkeyboard.model.Alphabets.N
import com.ait.customkeyboard.model.Alphabets.O
import com.ait.customkeyboard.model.Alphabets.P
import com.ait.customkeyboard.model.Alphabets.Q
import com.ait.customkeyboard.model.Alphabets.R
import com.ait.customkeyboard.model.Alphabets.S
import com.ait.customkeyboard.model.Alphabets.T
import com.ait.customkeyboard.model.Alphabets.U
import com.ait.customkeyboard.model.Alphabets.V
import com.ait.customkeyboard.model.Alphabets.W
import com.ait.customkeyboard.model.Alphabets.X
import com.ait.customkeyboard.model.Alphabets.Y
import com.ait.customkeyboard.model.Alphabets.Z
import com.ait.customkeyboard.model.Digit.Eight
import com.ait.customkeyboard.model.Digit.Five
import com.ait.customkeyboard.model.Digit.Four
import com.ait.customkeyboard.model.Digit.Nine
import com.ait.customkeyboard.model.Digit.One
import com.ait.customkeyboard.model.Digit.Seven
import com.ait.customkeyboard.model.Digit.Six
import com.ait.customkeyboard.model.Digit.Three
import com.ait.customkeyboard.model.Digit.Two
import com.ait.customkeyboard.model.Digit.Zero
import com.ait.customkeyboard.model.Key
import com.ait.customkeyboard.model.NumericUtilityKey
import com.ait.customkeyboard.model.SpecialCharactersKey.Ampersat
import com.ait.customkeyboard.model.SpecialCharactersKey.And
import com.ait.customkeyboard.model.SpecialCharactersKey.ArrowLeft
import com.ait.customkeyboard.model.SpecialCharactersKey.ArrowRight
import com.ait.customkeyboard.model.SpecialCharactersKey.Asterisk
import com.ait.customkeyboard.model.SpecialCharactersKey.BackSlash
import com.ait.customkeyboard.model.SpecialCharactersKey.Backlash
import com.ait.customkeyboard.model.SpecialCharactersKey.BoxBracketLeft
import com.ait.customkeyboard.model.SpecialCharactersKey.BoxBracketRight
import com.ait.customkeyboard.model.SpecialCharactersKey.Bullet
import com.ait.customkeyboard.model.SpecialCharactersKey.Caret
import com.ait.customkeyboard.model.SpecialCharactersKey.Cent
import com.ait.customkeyboard.model.SpecialCharactersKey.CheckMark
import com.ait.customkeyboard.model.SpecialCharactersKey.Colon
import com.ait.customkeyboard.model.SpecialCharactersKey.Comma
import com.ait.customkeyboard.model.SpecialCharactersKey.CopyRight
import com.ait.customkeyboard.model.SpecialCharactersKey.CurlyBracketLeft
import com.ait.customkeyboard.model.SpecialCharactersKey.CurlyBracketRight
import com.ait.customkeyboard.model.SpecialCharactersKey.Dash
import com.ait.customkeyboard.model.SpecialCharactersKey.Degree
import com.ait.customkeyboard.model.SpecialCharactersKey.Division
import com.ait.customkeyboard.model.SpecialCharactersKey.Dollar
import com.ait.customkeyboard.model.SpecialCharactersKey.Dot
import com.ait.customkeyboard.model.SpecialCharactersKey.Equal
import com.ait.customkeyboard.model.SpecialCharactersKey.Euro
import com.ait.customkeyboard.model.SpecialCharactersKey.Exclamation
import com.ait.customkeyboard.model.SpecialCharactersKey.Grave
import com.ait.customkeyboard.model.SpecialCharactersKey.Hash
import com.ait.customkeyboard.model.SpecialCharactersKey.Multiple
import com.ait.customkeyboard.model.SpecialCharactersKey.PI
import com.ait.customkeyboard.model.SpecialCharactersKey.Paragraph
import com.ait.customkeyboard.model.SpecialCharactersKey.ParenthesesBracketsLeft
import com.ait.customkeyboard.model.SpecialCharactersKey.ParenthesesBracketsRight
import com.ait.customkeyboard.model.SpecialCharactersKey.Percent
import com.ait.customkeyboard.model.SpecialCharactersKey.Pipe
import com.ait.customkeyboard.model.SpecialCharactersKey.Plus
import com.ait.customkeyboard.model.SpecialCharactersKey.Pound
import com.ait.customkeyboard.model.SpecialCharactersKey.Question
import com.ait.customkeyboard.model.SpecialCharactersKey.Quotes
import com.ait.customkeyboard.model.SpecialCharactersKey.RegisterTrademark
import com.ait.customkeyboard.model.SpecialCharactersKey.Root
import com.ait.customkeyboard.model.SpecialCharactersKey.Semicolon
import com.ait.customkeyboard.model.SpecialCharactersKey.SingleQuotes
import com.ait.customkeyboard.model.SpecialCharactersKey.Tide
import com.ait.customkeyboard.model.SpecialCharactersKey.Triangle
import com.ait.customkeyboard.model.SpecialCharactersKey.Underscore
import com.ait.customkeyboard.model.SpecialCharactersKey.Yen
import com.ait.customkeyboard.model.SuggestionKey
import com.ait.customkeyboard.model.TextUtilityKey.ABC
import com.ait.customkeyboard.model.TextUtilityKey.Numeric
import com.ait.customkeyboard.model.TextUtilityKey.SpecialCharacters
import com.ait.customkeyboard.model.UtilityKey.ActionArrow
import com.ait.customkeyboard.model.UtilityKey.Backspace
import com.ait.customkeyboard.model.UtilityKey.Clear
import com.ait.customkeyboard.model.UtilityKey.Space
import com.ait.customkeyboard.model.UtilityKey.Uppercase

object KeysDataSource {
    val normalKeys: List<Key> by lazy { constructNormalKeys() }
    val numericMiniKeys: List<Key> by lazy { constructNumericMiniKeys() }
    val numericKeys: List<Key> by lazy { constructNumericKeys() }
    val specialCharactersKeys: List<Key> by lazy { constructSpecialCharactersKeys() }
    val toggleKeys: List<Key> by lazy { createToggleButtonsList() }
    val emailSuggestions: List<Key> by lazy { createEmailSuggestions() }

    private fun createEmailSuggestions(): List<Key> = SuggestionHandler.emails.map {
        SuggestionKey(it, 3)
    }

    private fun createToggleButtonsList() = mutableListOf<Key>().apply {
        add(Uppercase)
    }

    private fun constructSpecialCharactersKeys() = mutableListOf<Key>().apply {
        // Row one
        add(Tide)
        add(Grave)
        add(Pipe)
        add(Bullet)
        add(Root)
        add(PI)
        add(Division)
        add(Multiple)
        add(Paragraph)
        add(Triangle)

        // Row two
        add(Pound)
        add(Cent)
        add(Euro)
        add(Yen)
        add(Caret)
        add(Degree)
        add(Equal)
        add(CurlyBracketLeft)
        add(CurlyBracketRight)
        add(Backlash)

        // Row three
        add(Numeric)
        add(Percent)
        add(CopyRight)
        add(RegisterTrademark)
        add(CheckMark)
        add(BoxBracketLeft)
        add(BoxBracketRight)
        add(ArrowLeft)
        add(ArrowRight)
        add(NumericUtilityKey.Backspace)

        // Row five
        add(ABC)
        add(Clear)
        add(Dot)
        add(Space)
        add(Comma)
        add(ActionArrow)
    }

    private fun constructNumericKeys() = mutableListOf<Key>().apply {
        // Row one
        add(One)
        add(Two)
        add(Three)
        add(Four)
        add(Five)
        add(Six)
        add(Seven)
        add(Eight)
        add(Nine)
        add(Zero)

        // Row two
        add(Ampersat)
        add(Hash)
        add(Dollar)
        add(Underscore)
        add(And)
        add(Dash)
        add(Plus)
        add(ParenthesesBracketsLeft)
        add(ParenthesesBracketsRight)
        add(BackSlash)

        // Row three
        add(SpecialCharacters)
        add(Asterisk)
        add(Quotes)
        add(SingleQuotes)
        add(Colon)
        add(Semicolon)
        add(Exclamation)
        add(Question)
        add(Percent)
        add(NumericUtilityKey.Backspace)

        // Row five
        add(ABC)
        add(Clear)
        add(Comma)
        add(Space)
        add(Dot)
        add(ActionArrow)
    }

    private fun constructNumericMiniKeys() = mutableListOf<Key>().apply {
        // Row one
        add(One)
        add(Two)
        add(Three)
        add(Dash)

        // Row two
        add(Four)
        add(Five)
        add(Six)
        add(NumericUtilityKey.Space)

        // Row three
        add(Seven)
        add(Eight)
        add(Nine)
        add(NumericUtilityKey.Backspace)

        // Row four
        add(Dot)
        add(Zero)
        add(Comma)
        add(NumericUtilityKey.RightArrow)
    }

    private fun constructNormalKeys() = mutableListOf<Key>().apply {

        // Row two
        add(Q)
        add(W)
        add(E)
        add(R)
        add(T)
        add(Y)
        add(U)
        add(I)
        add(O)
        add(P)

        // Row three
        add(A)
        add(S)
        add(D)
        add(F)
        add(G)
        add(H)
        add(J)
        add(K)
        add(L)
        add(Dot)

        // Row four
        add(Uppercase)
        add(Z)
        add(X)
        add(C)
        add(V)
        add(B)
        add(N)
        add(M)
        add(Comma)
        add(Backspace)

        // Row five
        add(Numeric)
        add(Clear)
        add(Underscore)
        add(Space)
        add(Dash)
        add(ActionArrow)
    }
}