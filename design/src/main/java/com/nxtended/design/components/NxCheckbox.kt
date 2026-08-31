package com.nxtended.design.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.R
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

private const val ANIMATION_DURATION = 200

@Composable
fun NxCheckbox(
    value: Boolean,
    onChanged: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val checkboxColor by animateColorAsState(
        label = "checkboxColor",
        targetValue = if (value) NxTheme.colors.text else Color.Transparent,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing
        ),
    )

    val checkmarkAlpha by animateFloatAsState(
        label = "checkmarkAlpha",
        targetValue = if (value) 1f else 0f,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing
        ),
    )

    val borderColor = if (enabled) {
        if (value) NxTheme.colors.text else NxTheme.colors.text.copy(alpha = 0.4f)
    } else NxTheme.colors.inactive

    Button(
        enabled = enabled,
        shape = CircleShape,
        onClick = { onChanged.invoke(!value) },
        modifier = Modifier.size(NxDimensions.InteractiveMin),
        interactionSource = remember { MutableInteractionSource() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(
                    color = if (enabled) checkboxColor else if (value) NxTheme.colors.inactive else Color.Transparent,
                    shape = NxShapes.Default
                ), contentAlignment = Alignment.Center
        ) {
            if (!value) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(NxShapes.Default)
                        .border(width = 2.dp, color = borderColor, shape = NxShapes.Default)
                )
            }

            Icon(
                painter = painterResource(R.drawable.nx_checkbox_check),
                contentDescription = "checkbox_checkmark",
                tint = NxTheme.colors.background,
                modifier = Modifier
                    .size(14.dp)
                    .graphicsLayer { alpha = checkmarkAlpha })
        }
    }
}

@Preview
@Composable
private fun NxCheckboxInteractivePreview() {
    NxTheme {
        Surface {
            var checked by remember { mutableStateOf(false) }

            Box(modifier = Modifier.padding(4.dp)) {
                NxCheckbox(value = checked, onChanged = { checked = it })
            }
        }

    }
}


@Preview
@Composable
private fun NxCheckboxLightPreview() {
    NxTheme {
        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxCheckbox(value = false, onChanged = {})
                NxCheckbox(value = true, onChanged = {})
                NxCheckbox(value = false, enabled = false, onChanged = {})
                NxCheckbox(value = true, enabled = false, onChanged = {})
            }
        }

    }
}


@Preview
@Composable
private fun NxCheckboxDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxCheckbox(value = false, onChanged = {})
                NxCheckbox(value = true, onChanged = {})
                NxCheckbox(value = false, enabled = false, onChanged = {})
                NxCheckbox(value = true, enabled = false, onChanged = {})
            }
        }
    }
}
