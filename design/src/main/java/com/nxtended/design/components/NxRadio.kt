package com.nxtended.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.theme.NxTheme

@Composable
fun NxRadio(
    value: Boolean,
    onChanged: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
) {
    val borderColor = when {
        value -> Color.Transparent
        enabled -> NxTheme.colors.onBackground
        else -> NxTheme.colors.onBackground.copy(alpha = 0.25f)
    }

    val fillColor = when {
        !value -> Color.Transparent
        enabled -> NxTheme.colors.onBackground
        else -> NxTheme.colors.onBackground.copy(alpha = 0.25f)
    }

    val dotColor = when {
        enabled -> NxTheme.colors.background
        else -> NxTheme.colors.background.copy(alpha = 0.5f)
    }


    Button(
        enabled = enabled,
        shape = CircleShape,
        onClick = { onChanged?.invoke(!value) },
        modifier = Modifier.size(48.dp),
        interactionSource = remember { MutableInteractionSource() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(20.dp)
                .background(shape = CircleShape, color = fillColor),
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = CircleShape,
                    ),
            )

            if (value) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = dotColor, shape = CircleShape),
                )
            }
        }
    }
}


@Preview
@Composable
private fun NxRadioInteractivePreview() {
    NxTheme {
        Surface {
            var checked by remember { mutableStateOf(false) }

            Box(modifier = Modifier.padding(4.dp)) {
                NxRadio(value = checked, onChanged = { checked = it })
            }
        }

    }
}


@Preview
@Composable
private fun NxRadioLightPreview() {
    NxTheme {
        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxRadio(value = false, onChanged = {})
                NxRadio(value = true, onChanged = {})
                NxRadio(value = false, enabled = false, onChanged = {})
                NxRadio(value = true, enabled = false, onChanged = {})
            }
        }

    }
}


@Preview
@Composable
private fun NxRadioDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxRadio(value = false, onChanged = {})
                NxRadio(value = true, onChanged = {})
                NxRadio(value = false, enabled = false, onChanged = {})
                NxRadio(value = true, enabled = false, onChanged = {})
            }
        }
    }
}
