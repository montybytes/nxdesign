package com.nxtended.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

@Composable
fun NxCard(
    modifier: Modifier = Modifier,
    color: Color = NxTheme.colors.surfaceVariant,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = NxShapes.Card,
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(0.dp),
        content = content
    )
}

@Preview
@Composable
private fun LightCardPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            NxCard(modifier = Modifier.padding(24.dp)) {
                Box(modifier = Modifier.size(400.dp, 200.dp)) {
                    Column(modifier = Modifier.padding(NxDimensions.MD)) {
                        Text("Nx Card")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun DarkCardPreview() {
    NxTheme(darkTheme = true) {
        Surface(color = NxTheme.colors.background) {
            NxCard(modifier = Modifier.padding(24.dp)) {
                Box(modifier = Modifier.size(400.dp, 200.dp)) {
                    Column(modifier = Modifier.padding(NxDimensions.MD)) {
                        Text("Nx Card")
                    }
                }
            }

        }
    }
}