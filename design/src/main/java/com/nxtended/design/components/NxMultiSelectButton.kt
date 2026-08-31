package com.nxtended.design.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.R
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

data class NxMultiSelectItem<T>(
    val label: String,
    val data: T,
    val icon: (@Composable () -> Unit)? = null,
)

@Composable
fun <T> NxMultiSelectButton(
    modifier: Modifier = Modifier,
    selected: T,
    selectables: List<NxMultiSelectItem<T>>,
    onSelectionChanged: ((T) -> Unit)? = null,
    enabled: Boolean = true,
) {
    val outerShape = NxShapes.Large

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(outerShape)
            .border(
                width = 1.dp,
                color = Color.Gray.copy(alpha = 80f / 255f),
                shape = outerShape,
            )
            .padding(1.dp),
    ) {
        selectables.forEachIndexed { index, item ->
            val isSelected = selected == item.data

            val shape = RoundedCornerShape(
                topStart = if (index == 0) 11.dp else 0.dp,
                bottomStart = if (index == 0) 11.dp else 0.dp,
                topEnd = if (index == selectables.lastIndex) 11.dp else 0.dp,
                bottomEnd = if (index == selectables.lastIndex) 11.dp else 0.dp,
            )

            Button(
                enabled = enabled,
                shape = shape,
                onClick = { onSelectionChanged?.invoke(item.data) },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = NxTheme.colors.text,
                    containerColor = if (isSelected) Color.Gray.copy(alpha = 0.3f) else Color.Transparent
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    item.icon?.invoke()

                    Text(text = item.label)
                }
            }
        }
    }
}

@Preview
@Composable
private fun NxMultiSelectButtonPreview() {
    NxTheme {
        var selected by remember { mutableStateOf("Grid") }

        val items = listOf(
            NxMultiSelectItem(
                label = "List", data = "List", icon = { NxIcon(icon = R.drawable.nx_listview) }),
            NxMultiSelectItem(
                label = "Grid",
                data = "Grid",
            ),
            NxMultiSelectItem(
                label = "Day",
                data = "Day",
            ),
            NxMultiSelectItem(
                label = "Month",
                data = "Month",
            ),
        )

        Surface(color = NxTheme.colors.background) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(NxDimensions.MD),
            ) {
                NxMultiSelectButton(
                    selected = selected,
                    selectables = items,
                    onSelectionChanged = { selected = it },
                )
            }
        }
    }
}

@Preview
@Composable
private fun NxMultiSelectButtonDarkPreview() {
    NxTheme(darkTheme = true) {
        var selected by remember { mutableStateOf("Grid") }

        val items = listOf(
            NxMultiSelectItem(
                label = "List", data = "List", icon = { NxIcon(icon = R.drawable.nx_listview) }),
            NxMultiSelectItem(
                label = "Grid",
                data = "Grid",
            ),
            NxMultiSelectItem(
                label = "Day",
                data = "Day",
            ),
            NxMultiSelectItem(
                label = "Month",
                data = "Month",
            ),
        )

        Surface(color = NxTheme.colors.background) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(NxDimensions.MD),
            ) {
                NxMultiSelectButton(
                    selected = selected,
                    selectables = items,
                    onSelectionChanged = { selected = it },
                )
            }
        }
    }
}
