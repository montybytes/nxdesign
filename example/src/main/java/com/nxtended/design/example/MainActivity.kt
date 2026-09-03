package com.nxtended.design.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxtended.design.components.NxAppbar
import com.nxtended.design.components.NxCard
import com.nxtended.design.components.NxCheckbox
import com.nxtended.design.components.NxDismissible
import com.nxtended.design.components.NxIcon
import com.nxtended.design.components.NxMultiSelectButton
import com.nxtended.design.components.NxMultiSelectItem
import com.nxtended.design.components.NxPillButton
import com.nxtended.design.components.NxRadio
import com.nxtended.design.components.NxSwitch
import com.nxtended.design.components.NxTextButton
import com.nxtended.design.foundation.colors.NxColors
import com.nxtended.design.foundation.dimensions.NxDimensions
import com.nxtended.design.foundation.icons.NxIcons
import com.nxtended.design.foundation.shapes.listItemShape
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
    var selected by remember { mutableStateOf("List") }

    val items = listOf(
        NxMultiSelectItem(
            label = "List",
            data = "List",
            icon = { NxIcon(icon = com.nxtended.design.R.drawable.nx_listview) }),
        NxMultiSelectItem(
            label = "Grid",
            data = "Grid",
            icon = { NxIcon(icon = com.nxtended.design.R.drawable.nx_gridview) }),
        NxMultiSelectItem(
            label = "Day",
            data = "Day",
            icon = { NxIcon(icon = com.nxtended.design.R.drawable.nx_day_view) }),
    )

    var dismissibleItems by remember {
        mutableStateOf(
            listOf(
                "Message One", "Message Two", "Message Three", "Message Four", "Message Five"
            )
        )
    }

    NxTheme {
        Surface(color = NxTheme.colors.background) {
            Column(Modifier.fillMaxSize()) {
                NxAppbar(title = "NxDesign", actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f),
                    ) {
                        NxIcon(
                            icon = NxIcons.Settings,
                            contentDescription = "Settings",
                        )
                    }
                })

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(NxDimensions.MD), verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "Component Showcase")

                    Box(modifier = Modifier.height(16.dp))

                    NxCard {
                        Column(
                            modifier = Modifier
                                .padding(NxDimensions.MD)
                                .fillMaxWidth()
                        ) {
                            Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since 1966, when designers at Letraset and James Mosley, the librarian at St Bride Printing Library in London, took a 1914 Cicero translation and scrambled it to make dummy text for Letraset's Body Type sheets")
                        }
                    }

                    NxCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(NxDimensions.MD),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text("Buttons")

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                NxPillButton(text = "Pill Button", onClick = {})
                                NxPillButton(onClick = {}, text = "Log Out", enabled = false)
                                NxPillButton(onClick = {}, text = "Delete", isDestructive = true)
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                NxTextButton(text = "Text Button", onClick = {})
                                NxTextButton(text = "Disabled", enabled = false, onClick = {})
                                NxTextButton(text = "Delete", isDestructive = true, onClick = {})
                            }

                            NxMultiSelectButton(
                                selected = selected,
                                selectables = items,
                                onSelectionChanged = { selected = it },
                            )
                        }
                    }

                    NxCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(NxDimensions.MD),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Text("Checkbox")

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(vertical = NxDimensions.XS)
                            ) {
                                NxCheckbox(value = false, onChanged = {})
                                NxCheckbox(value = true, onChanged = {})
                                NxCheckbox(value = false, enabled = false, onChanged = {})
                                NxCheckbox(value = true, enabled = false, onChanged = {})
                            }

                            Text("Radio")

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(vertical = NxDimensions.XS)
                            ) {
                                NxRadio(value = false, onChanged = {})
                                NxRadio(value = true, onChanged = {})
                                NxRadio(value = false, enabled = false, onChanged = {})
                                NxRadio(value = true, enabled = false, onChanged = {})
                            }

                            Text("Switch")

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(vertical = NxDimensions.XS)
                            ) {
                                NxSwitch(value = false, onChanged = {})
                                NxSwitch(value = true, onChanged = {})
                                NxSwitch(value = false, enabled = false, onChanged = {})
                                NxSwitch(value = true, enabled = false, onChanged = {})
                            }
                        }
                    }

                    Box(Modifier.padding(bottom = 16.dp, top = 14.dp)) {
                        Text("Dismissibles")
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(NxDimensions.ListItemSpacer)
                    ) {
                        dismissibleItems.forEachIndexed { index, item ->
                            key(item) {
                                NxDismissible(
                                    confirmDismiss = { true },
                                    onDismissed = { dismissibleItems = dismissibleItems - item },
                                    background = {
                                        Surface(
                                            color = NxColors.NothingRed,
                                            shape = listItemShape(index, dismissibleItems.size),
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
                                        shape = listItemShape(index, dismissibleItems.size)
                                    ) {
                                        Text(
                                            text = item,
                                            modifier = Modifier.padding(
                                                horizontal = NxDimensions.MD,
                                                vertical = NxDimensions.LG
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
    }
}