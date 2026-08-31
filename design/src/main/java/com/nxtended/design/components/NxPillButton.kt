package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
    enabled: Boolean = true,
    shape: Shape = NxPillButtonDefaults.shape,
    textColor: Color = NxPillButtonDefaults.textColor(),
    buttonColor: Color = NxPillButtonDefaults.buttonColor()
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(
                minHeight = NxPillButtonDefaults.minHeight,
                minWidth = 160.dp,
            )
            .heightIn(NxPillButtonDefaults.minHeight)
            .widthIn(200.dp),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = buttonColor,
            disabledContentColor = NxTheme.colors.inactive,
            disabledContainerColor = buttonColor,
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

object NxPillButtonDefaults {
    val minHeight = 48.dp
    val shape = NxShapes.Pill

    @Composable
    fun textColor() = NxTheme.colors.text

    @Composable
    fun buttonColor() = NxTheme.colors.card
}

@Preview
@Composable
private fun PillButtonLightPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    NxPillButton(text = "Pill Button", onClick = {})

                    NxPillButton(
                        onClick = {},
                        text = "Log Out",
                        textColor = NxColors.NothingRed,
                        enabled = false
                    )

                    NxPillButton(
                        onClick = {},
                        text = "Delete",
                        textColor = NxColors.DarkText,
                        buttonColor = NxColors.NothingRed,
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
        Surface(color = NxTheme.colors.background) {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    NxPillButton(text = "Pill Button", onClick = {})

                    NxPillButton(
                        onClick = {},
                        text = "Log Out",
                        textColor = NxColors.NothingRed,
                        enabled = false
                    )

                    NxPillButton(
                        onClick = {},
                        text = "Delete",
                        textColor = NxColors.DarkText,
                        buttonColor = NxColors.NothingRed,
                    )
                }
            }
        }
    }
}
