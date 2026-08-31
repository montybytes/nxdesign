package com.nxtended.design.foundation.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.nxtended.design.R

enum class NxFont {
    System,
    Internal
}

val default = FontFamily(
    Font(R.font.inter)
)

object NxFonts {
    val Inter = FontFamily(Font(R.font.inter))

    val InterDigits = FontFamily(Font(R.font.intermonodigits))

    val NType = FontFamily(Font(R.font.ntype82))

    val NTypeDigits = FontFamily(Font(R.font.ntype82monodigits))

    val NDot = FontFamily(Font(R.font.ndot77))

    val NDotDigits = FontFamily(Font(R.font.ndot77monodigits))

    val Lettera = FontFamily(Font(R.font.letteramono))

    val SpaceMono = FontFamily(Font(R.font.spacemono))

    val SpaceGrotesk = FontFamily(Font(R.font.spacegrotesk))

    val Geist = FontFamily(Font(R.font.geist))
}