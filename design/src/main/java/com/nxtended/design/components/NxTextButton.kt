package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.theme.NxTheme

@Composable
fun NxTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isDestructive: Boolean = false,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = 32.dp)
            .heightIn(32.dp),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (isDestructive) NxColors.NothingRed else NxTheme.colors.onBackground,
        ),
    ) {
        Text(
            text = text,
            maxLines = 2,
            style = NxTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}


@Preview
@Composable
private fun TextButtonLightPreview() {
    NxTheme {
        Surface {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column {
                    NxTextButton(text = "Text Button", onClick = {})

                    NxTextButton(text = "Disabled", enabled = false, onClick = {})

                    NxTextButton(text = "Delete", isDestructive = true, onClick = {})
                }
            }
        }
    }
}


@Preview
@Composable
private fun TextButtonDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface {
            Box(modifier = Modifier.padding(NxDimensions.MD)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    NxTextButton(text = "Text Button", onClick = {})

                    NxTextButton(text = "Disabled", enabled = false, onClick = {})

                    NxTextButton(text = "Delete", isDestructive = true, onClick = {})
                }
            }
        }
    }
}
