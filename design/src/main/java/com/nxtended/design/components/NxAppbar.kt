package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.icons.NxIcons
import com.nxtended.design.theme.NxTheme

@Composable
fun NxAppbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle? = null,
    padding: Dp = 0.dp,
    actions: (@Composable () -> Unit)? = null,
    leadingOverride: (@Composable () -> Unit)? = null,
    onNavigateBack: (() -> Unit)? = null,
) {
    val hasTitle = title != null

    Surface(
        color = NxTheme.colors.background,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = if (onNavigateBack != null || hasTitle) 0.dp else padding)
        ) {
            if (onNavigateBack != null) {
                if (leadingOverride != null) leadingOverride()
                else IconButton(
                    onClick = { onNavigateBack.invoke() },
                    modifier = Modifier
                        .heightIn(48.dp)
                        .aspectRatio(1f),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                ) {
                    NxIcon(
                        icon = NxIcons.Back,
                        contentDescription = "Back",
                    )
                }
            }

            if (hasTitle) {
                Text(
                    text = title,
                    style = titleStyle ?: NxTheme.typography.titleLarge,
                    color = NxTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = if (onNavigateBack == null) 24.dp else 0.dp),
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }


            if (actions != null) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    actions()
                }
            }

        }
    }
}

@Preview
@Composable
private fun NxAppbarPreview() {
    NxTheme {
        Surface(
            color = NxTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                NxAppbar(title = "Example", onNavigateBack = {}, actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .heightIn(48.dp)
                            .aspectRatio(1f),
                    ) {
                        NxIcon(
                            icon = NxIcons.Settings,
                            contentDescription = "Back",
                        )
                    }
                })

                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hello, world!", style = NxTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun NxAppbarDarkPreview() {
    NxTheme(darkTheme = true) {
        Surface(
            color = NxTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                NxAppbar(title = "Example", onNavigateBack = {}, actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f),
                    ) {
                        NxIcon(
                            icon = NxIcons.Settings,
                            contentDescription = "Back",
                        )
                    }
                })

                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hello, world!", style = NxTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}