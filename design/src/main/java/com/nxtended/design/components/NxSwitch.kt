package com.nxtended.design.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.theme.NxTheme

private const val ANIMATION_DURATION = 250

@Composable
fun NxSwitch(
    value: Boolean,
    onChanged: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val activeColor = NxTheme.colors.text
    val inactiveColor = NxTheme.colors.inactive
    val thumbColor = NxTheme.colors.background

    val trackColor by animateColorAsState(
        label = "switchTrackColor",
        targetValue = if (value) {
            if (enabled) activeColor else NxTheme.colors.text.copy(alpha = 0.4f)
        } else {
            if (enabled) inactiveColor else NxTheme.colors.text.copy(alpha = 0.4f)
        },
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing
        ),
    )

    val thumbOffset by animateDpAsState(
        label = "switchThumbOffset",
        targetValue = if (value) 24.dp else 0.dp,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing
        ),
    )

    Button(
        enabled = enabled,
        shape = CircleShape,
        onClick = { onChanged.invoke(!value) },
        modifier = Modifier.size(48.dp),
        interactionSource = remember { MutableInteractionSource() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(trackColor)
                .padding(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = thumbOffset)
                    .clip(CircleShape)
                    .background(if (enabled) thumbColor else NxTheme.colors.inactive)
            )
        }
    }
}

@Preview
@Composable
private fun NxSwitchInteractivePreview() {
    NxTheme {
        Surface {
            var checked by remember { mutableStateOf(false) }

            Box(modifier = Modifier.padding(4.dp)) {
                NxSwitch(value = checked, onChanged = { checked = it })
            }
        }

    }
}

@Preview
@Composable
private fun NxSwitchLightPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxSwitch(value = false, onChanged = {})
                NxSwitch(value = true, onChanged = {})
                NxSwitch(value = false, enabled = false, onChanged = {})
                NxSwitch(value = true, enabled = false, onChanged = {})
            }
        }

    }
}


@Preview
@Composable
private fun NxSwitchDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface(color = NxTheme.colors.background) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                NxSwitch(value = false, onChanged = {})
                NxSwitch(value = true, onChanged = {})
                NxSwitch(value = false, enabled = false, onChanged = {})
                NxSwitch(value = true, enabled = false, onChanged = {})
            }
        }
    }
}