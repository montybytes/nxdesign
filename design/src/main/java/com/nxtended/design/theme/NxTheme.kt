package com.nxtended.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.nxtended.design.foundation.colors.NxColors

object NxTheme {
    val colors @Composable @ReadOnlyComposable get() = MaterialTheme.colorScheme
    val typography @Composable @ReadOnlyComposable get() = MaterialTheme.typography
}

@Composable
fun NxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {

    val materialColors = if (darkTheme) {
        darkColorScheme(
            primary = NxColors.NothingRed,
            onPrimary = NxColors.DarkText,

            secondary = NxColors.NothingYellow,
            onSecondary = NxColors.LightText,

            tertiary = NxColors.DarkButton,
            onTertiary = NxColors.DarkText,

            background = NxColors.DarkBackground,
            onBackground = NxColors.DarkText,

            surface = NxColors.DarkSurface,
            onSurface = NxColors.DarkText,

            surfaceVariant = NxColors.DarkSurfaceVariant,
            onSurfaceVariant = NxColors.DarkText,

            surfaceContainer = NxColors.DarkDialogSurface,

            error = NxColors.NothingRed,
        )
    } else {
        lightColorScheme(
            primary = NxColors.NothingRed,
            onPrimary = NxColors.DarkText,

            secondary = NxColors.NothingYellow,
            onSecondary = NxColors.LightText,

            tertiary = NxColors.LightButton,
            onTertiary = NxColors.LightText,

            background = NxColors.LightBackground,
            onBackground = NxColors.LightText,

            surface = NxColors.LightSurface,
            onSurface = NxColors.LightText,

            surfaceVariant = NxColors.LightSurfaceVariant,
            onSurfaceVariant = NxColors.LightText,

            surfaceContainer = NxColors.LightDialogSurface,

            error = NxColors.NothingRed,
        )
    }

    MaterialTheme(
        colorScheme = materialColors, typography = NxTypography, content = content
    )
}