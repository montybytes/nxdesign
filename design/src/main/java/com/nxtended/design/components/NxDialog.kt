package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

@Composable
fun NxDialog(
    dismissible: Boolean = true,
    onDismiss: (() -> Unit)? = null,
    contentSpacing: Dp = 8.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss?.invoke() }, properties = DialogProperties(
            dismissOnBackPress = dismissible,
            dismissOnClickOutside = dismissible,
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = NxShapes.Card,
            color = NxTheme.colors.card,
            tonalElevation = 0.dp,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(contentSpacing),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = content
            )
        }
    }
}


@Preview
@Composable
private fun DialogPreview() {
    NxTheme {
        NxDialog {
            Text("This is a sample Dialog")

            Text("You can use this to display any kind of information via the the content")

            Text("For example a button")

            Button(
                onClick = {},
                shape = NxShapes.Large,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NxTheme.colors.listItem, contentColor = NxTheme.colors.text
                )
            ) {
                Text("Sample Button")
            }
        }
    }

}
