package com.nxtended.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.nxtended.design.foundation.colors.NxColors

object NxTheme {
    val colors @Composable @ReadOnlyComposable get() = LocalColors.current
    val typography @Composable @ReadOnlyComposable get() = MaterialTheme.typography
}

@Composable
fun NxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) NxDarkColors else NxLightColors

    val materialColors = if (darkTheme) {
        darkColorScheme(
            primary = NxColors.NothingRed,
            onPrimary = NxColors.DarkText,
            secondary = NxColors.NothingYellow,
            onSecondary = NxColors.DarkText,
            background = NxColors.DarkBackground,
            onBackground = NxColors.DarkText,
            surface = NxColors.DarkCard,
            onSurface = NxColors.DarkText
        )
    } else {
        lightColorScheme(
            primary = NxColors.NothingRed,
            onPrimary = NxColors.DarkText,
            secondary = NxColors.NothingYellow,
            onSecondary = NxColors.DarkText,
            background = NxColors.LightBackground,
            onBackground = NxColors.LightText,
            surface = NxColors.LightCard,
            onSurface = NxColors.LightText
        )
    }


    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(colorScheme = materialColors, typography = NxTypography, content = content)
    }
}