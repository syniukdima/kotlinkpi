package com.example.labs_mobile.labs.lab4.screens

import com.example.labs_mobile.labs.lab4.components.CableSelectionTab
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.labs_mobile.labs.lab4.components.HPnEMShortCircuitTab
import com.example.labs_mobile.labs.lab4.components.ShortCircuitTab


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShortCircuitCalculatorScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Вибір кабелю", "Струм КЗ", "Струм КЗ ХПнЕМ")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Калькулятор") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> CableSelectionTab()
                1 -> ShortCircuitTab()
                2 -> HPnEMShortCircuitTab()
            }
        }
    }
}
