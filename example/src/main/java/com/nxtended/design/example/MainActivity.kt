package com.nxtended.design.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.components.NxCard
import com.nxtended.design.components.NxCheckbox
import com.nxtended.design.components.NxPillButton
import com.nxtended.design.components.NxRadio
import com.nxtended.design.components.NxSwitch
import com.nxtended.design.components.NxTextButton
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.theme.NxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NxTheme {
                Surface(color = NxTheme.colors.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                    ) {
                        DesignSystemDemo()
                    }
                }
            }
        }
    }
}

@Preview

@Composable
private fun DesignSystemDemo() {
    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(NxDimensions.MD), verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(text = "NxDesign")

                Text(text = "Component Showcase")

                // Card

                NxCard {
                    Column(
                        modifier = Modifier
                            .padding(NxDimensions.MD)
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Nx Card")
                    }
                }

                // Buttons

                NxCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(NxDimensions.MD),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text("Buttons")

                        NxPillButton(text = "Pill Button", onClick = {})

                        NxPillButton(
                            onClick = {},
                            text = "Log Out",
                            textColor = NxColors.NothingRed,
                            enabled = false
                        )

                        NxPillButton(
                            onClick = {},
                            text = "Delete",
                            textColor = NxColors.DarkText,
                            buttonColor = NxColors.NothingRed,
                        )

                        NxTextButton(text = "Text Button", onClick = {})

                        NxTextButton(text = "Disabled", enabled = false, onClick = {})

                        NxTextButton(text = "Delete", textColor = NxColors.NothingRed, onClick = {})
                    }
                }

                // Checkbox

                NxCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(NxDimensions.MD),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text("Checkbox")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(NxDimensions.MD)
                        ) {
                            NxCheckbox(value = false, onChanged = {})
                            NxCheckbox(value = true, onChanged = {})
                            NxCheckbox(value = false, enabled = false, onChanged = {})
                            NxCheckbox(value = true, enabled = false, onChanged = {})
                        }
                    }
                }


                // Radio

                NxCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(NxDimensions.MD),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text("Radio")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(NxDimensions.MD)
                        ) {
                            NxRadio(value = false, onChanged = {})
                            NxRadio(value = true, onChanged = {})
                            NxRadio(value = false, enabled = false, onChanged = {})
                            NxRadio(value = true, enabled = false, onChanged = {})
                        }
                    }

                }

                // Switch

                NxCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(NxDimensions.MD),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text("Switch")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(NxDimensions.MD)
                        ) {
                            NxSwitch(value = false, onChanged = {})
                            NxSwitch(value = true, onChanged = {})
                            NxSwitch(value = false, enabled = false, onChanged = {})
                            NxSwitch(value = true, enabled = false, onChanged = {})
                        }
                    }
                }
            }
        }
    }
}