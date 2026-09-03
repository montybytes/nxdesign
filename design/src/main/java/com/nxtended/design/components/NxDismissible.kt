package com.nxtended.design.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.icons.NxIcons
import com.nxtended.design.foundation.shapes.listItemShape
import com.nxtended.design.theme.NxTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

private const val DISMISS_ANIMATION_DURATION = 200

@Composable
fun NxDismissible(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onDismissed: () -> Unit,
    dismissThreshold: Float = 0.3f,
    confirmDismiss: (suspend () -> Boolean)? = null,
    background: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    val offsetX = remember { Animatable(0f) }

    var widthPx by remember { mutableIntStateOf(0) }

    var contentHeight by remember { mutableIntStateOf(0) }

    var dismissed by remember { mutableStateOf(false) }

    var dismissing by remember { mutableStateOf(false) }

    suspend fun animateBack() {
        offsetX.animateTo(
            targetValue = 0f, animationSpec = tween(
                durationMillis = DISMISS_ANIMATION_DURATION, easing = FastOutSlowInEasing
            )
        )
    }

    suspend fun completeDismiss() {
        if (dismissing || dismissed) return

        dismissing = true

        offsetX.animateTo(
            targetValue = -widthPx.toFloat(), animationSpec = tween(
                durationMillis = DISMISS_ANIMATION_DURATION, easing = FastOutSlowInEasing
            )
        )

        dismissed = true
        onDismissed()
    }

    if (dismissed) return

    Box(modifier = modifier
        .fillMaxWidth()
        .onSizeChanged { widthPx = it.width }
        .pointerInput(enabled, dismissing, widthPx) {
            if (!enabled || dismissing || widthPx == 0) return@pointerInput


            detectHorizontalDragGestures(
                onHorizontalDrag = { change, dragAmount ->
                    change.consume()

                    scope.launch {
                        offsetX.snapTo(
                            (offsetX.value + dragAmount).coerceIn(-widthPx.toFloat(), 0f)
                        )
                    }
                },

                onDragEnd = {
                    scope.launch {
                        val progress = offsetX.value.absoluteValue / widthPx.toFloat()

                        if (progress < dismissThreshold) {
                            animateBack()
                            return@launch
                        }

                        val allowed = confirmDismiss?.invoke() ?: true

                        if (allowed) completeDismiss() else animateBack()
                    }
                },

                onDragCancel = { scope.launch { animateBack() } })
        }) {

        //val revealWidth = with(density) { offsetX.value.absoluteValue.toDp() }

        val contentHeightDp = with(density) { contentHeight.toDp() }

        // Background
        Box(
            content = background,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth()
                .height(contentHeightDp)
        )

        // Foreground
        Box(
            content = content,
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { contentHeight = it.height }
                .offset { IntOffset(x = offsetX.value.toInt(), y = 0) },
        )
    }
}

@Preview
@Composable
private fun NxDismissiblePreview() {
    NxTheme {
        var items by remember {
            mutableStateOf(
                listOf(
                    "Message One", "Message Two", "Message Three", "Message Four", "Message Five"
                )
            )
        }
        Surface(color = NxTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(NxDimensions.MD),
                verticalArrangement = Arrangement.spacedBy(NxDimensions.ListItemSpacer)
            ) {
                items.forEachIndexed { index, item ->
                    key(item) {
                        NxDismissible(
                            confirmDismiss = { true },
                            onDismissed = { items = items - item },
                            background = {
                                Surface(
                                    color = NxColors.NothingRed,
                                    shape = listItemShape(index, items.size),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(start = NxDimensions.ListItemSpacer)
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        NxIcon(
                                            icon = NxIcons.DeleteSwipe,
                                            tint = NxColors.DarkText,
                                            modifier = Modifier.padding(end = NxDimensions.LG)
                                        )
                                    }
                                }
                            },
                        ) {
                            Surface(
                                contentColor = NxTheme.colors.onSurface,
                                modifier = Modifier.fillMaxWidth(),
                                shape = listItemShape(index, items.size)
                            ) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(
                                        horizontal = NxDimensions.MD, vertical = NxDimensions.LG
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun NxDismissibleDarkPreview() {
    NxTheme(darkTheme = true) {
        var items by remember {
            mutableStateOf(
                listOf(
                    "Message One", "Message Two", "Message Three", "Message Four", "Message Five"
                )
            )
        }
        Surface(color = NxTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(NxDimensions.MD),
                verticalArrangement = Arrangement.spacedBy(NxDimensions.ListItemSpacer)
            ) {
                items.forEachIndexed { index, item ->
                    key(item) {
                        NxDismissible(
                            confirmDismiss = { true },
                            onDismissed = { items = items - item },
                            background = {
                                Surface(
                                    color = NxColors.NothingRed,
                                    shape = listItemShape(index, items.size),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(start = NxDimensions.ListItemSpacer)
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        NxIcon(
                                            icon = NxIcons.DeleteSwipe,
                                            tint = NxColors.DarkText,
                                            modifier = Modifier.padding(end = NxDimensions.LG)
                                        )
                                    }
                                }
                            },
                        ) {
                            Surface(
                                contentColor = NxTheme.colors.onSurface,
                                modifier = Modifier.fillMaxWidth(),
                                shape = listItemShape(index, items.size)
                            ) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(
                                        horizontal = NxDimensions.MD, vertical = NxDimensions.LG
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}