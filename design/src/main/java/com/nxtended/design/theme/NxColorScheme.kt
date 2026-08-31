package com.nxtended.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.nxtended.design.foundation.colors.NxColors

@Immutable
data class NxColorScheme(
    val text: Color,
    val background: Color,
    val card: Color,
    val listItem: Color,
    val inactive: Color,
    val primary: Color,
    val secondary: Color
)

val NxLightColors = NxColorScheme(
    text = NxColors.LightText,
    background = NxColors.LightBackground,
    card = NxColors.LightCard,
    listItem = NxColors.LightListItem,
    inactive = NxColors.LightInactive,
    primary = NxColors.NothingRed,
    secondary = NxColors.NothingYellow
)

val NxDarkColors = NxColorScheme(
    text = NxColors.DarkText,
    background = NxColors.DarkBackground,
    card = NxColors.DarkCard,
    listItem = NxColors.DarkListItem,
    inactive = NxColors.DarkInactive,
    primary = NxColors.NothingRed,
    secondary = NxColors.NothingYellow
)

internal val LocalColors = staticCompositionLocalOf<NxColorScheme> { error("No Theme provided") }