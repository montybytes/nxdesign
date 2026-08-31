package com.nxtended.design.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.icons.NxIcons
import com.nxtended.design.theme.NxTheme

@Composable
fun NxIcon(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    size: Dp = 26.dp,
    selected: Boolean = false,
    tint: Color? = null,
    contentDescription: String? = null
) {

    val actualTint = when {
        selected -> NxTheme.colors.primary
        tint != null -> tint
        else -> NxTheme.colors.text
    }

    Icon(
        painter = painterResource(icon),
        contentDescription = contentDescription,
        tint = actualTint,
        modifier = modifier.size(size)
    )
}

@Preview
@Composable
private fun LightThemeIconPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Row(
                modifier = Modifier.padding(NxDimensions.MD),
                horizontalArrangement = Arrangement.spacedBy(NxDimensions.SM)
            ) {
                NxIcon(icon = NxIcons.Home)
                NxIcon(icon = NxIcons.Search, selected = true)
                NxIcon(icon = NxIcons.Settings)
                NxIcon(icon = NxIcons.Delete)
            }
        }
    }
}

@Preview
@Composable
private fun DarkThemeIconPreview() {
    NxTheme(darkTheme = true) {
        Surface(color = NxTheme.colors.background) {
            Row(
                modifier = Modifier.padding(NxDimensions.MD),
                horizontalArrangement = Arrangement.spacedBy(NxDimensions.SM)
            ) {
                NxIcon(icon = NxIcons.Home)
                NxIcon(icon = NxIcons.Search, selected = true)
                NxIcon(icon = NxIcons.Settings)
                NxIcon(icon = NxIcons.Delete)
            }
        }
    }
}
