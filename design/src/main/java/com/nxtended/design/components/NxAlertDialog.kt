package com.nxtended.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.shapes.NxShapes
import com.nxtended.design.theme.NxTheme

@Composable
fun NxAlertDialog(
    titleText: String,
    infoText: String,
    confirmText: String = "OK",
    cancelText: String = "CANCEL",
    isWarning: Boolean = false,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    dismissible: Boolean = false,
) {
    Dialog(
        onDismissRequest = onCancel, properties = DialogProperties(
            dismissOnBackPress = dismissible,
            dismissOnClickOutside = dismissible,
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = NxShapes.Card,
            color = NxTheme.colors.surfaceContainer,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 48.dp, vertical = 16.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(
                        8.dp
                    ),
                ) {
                    Text(
                        text = titleText,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = NxTheme.typography.titleLarge,
                        color = NxTheme.colors.onSurface,
                    )

                    Text(
                        text = infoText,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = NxTheme.typography.bodyMedium,
                        color = NxTheme.colors.onSurface,
                    )
                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(NxDimensions.ListItemSpacer),
                ) {
                    Button(
                        onClick = onConfirm,
                        shape = NxShapes.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NxTheme.colors.tertiary,
                            contentColor = if (isWarning) NxColors.NothingRed else NxTheme.colors.onTertiary
                        )
                    ) {
                        Text(confirmText)
                    }

                    Button(
                        onClick = onCancel,
                        shape = NxShapes.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NxTheme.colors.tertiary,
                            contentColor = NxTheme.colors.onTertiary
                        )
                    ) {
                        Text(cancelText)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun AlertDialogPreview() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            NxAlertDialog(
                titleText = "Alert Dialog",
                infoText = "This is a sample Alert Dialog useful for showing a prompt to a user",
                isWarning = true,
                onConfirm = {},
                onCancel = {})
        }

    }
}