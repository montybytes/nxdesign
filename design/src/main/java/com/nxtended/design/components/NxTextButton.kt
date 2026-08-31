package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

@Composable
fun NxTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = NxTheme.colors.text,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = 32.dp)
            .heightIn(32.dp),
        enabled = enabled,
        shape = NxShapes.Pill,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
    ) {
        Text(
            text = text,
            maxLines = 2,
            style = NxTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis,
            color = if (!enabled) NxTheme.colors.inactive else textColor,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}


@Preview
@Composable
private fun TextButtonLightPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column {
                    NxTextButton(text = "Text Button", onClick = {})

                    NxTextButton(text = "Disabled", enabled = false, onClick = {})

                    NxTextButton(text = "Delete", textColor = NxColors.NothingRed, onClick = {})
                }
            }
        }
    }
}


@Preview
@Composable
private fun TextButtonDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface(color = NxTheme.colors.background) {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    NxTextButton(text = "Text Button", onClick = {})

                    NxTextButton(text = "Disabled", enabled = false, onClick = {})

                    NxTextButton(text = "Delete", textColor = NxColors.NothingRed, onClick = {})
                }
            }
        }
    }
}
