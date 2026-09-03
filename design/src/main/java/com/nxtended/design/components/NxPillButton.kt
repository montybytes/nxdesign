package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

@Composable
fun NxPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDestructive: Boolean = false,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = NxShapes.Pill,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDestructive) NxColors.NothingRed else NxTheme.colors.tertiary,
            contentColor = if (isDestructive) NxColors.DarkText else NxTheme.colors.onTertiary
        )
    ) {
        Text(
            text = text,
            maxLines = 2,
            style = NxTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
private fun PillButtonLightPreview() {
    NxTheme {
        Surface {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    NxPillButton(text = "Pill Button", onClick = {})

                    NxPillButton(
                        onClick = {}, text = "Log Out", enabled = false
                    )

                    NxPillButton(
                        onClick = {},
                        isDestructive = true,
                        text = "Delete",
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PillButtonDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    NxPillButton(text = "Pill Button", onClick = {})

                    NxPillButton(
                        onClick = {}, text = "Log Out", enabled = false
                    )

                    NxPillButton(
                        onClick = {},
                        isDestructive = true,
                        text = "Delete",
                    )
                }
            }
        }
    }
}
