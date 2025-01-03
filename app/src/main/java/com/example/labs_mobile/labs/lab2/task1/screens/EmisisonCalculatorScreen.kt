package com.example.labs_mobile.labs.lab2.task1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.labs_mobile.labs.lab2.task1.components.CoalForm
import com.example.labs_mobile.labs.lab2.task1.components.FuelOilForm
import com.example.labs_mobile.labs.lab2.task1.components.NaturalGasForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmissionCalculatorScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Вугілля", "Мазут", "Природний газ")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Розрахунок валових викидів") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
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
                0 -> CoalForm()
                1 -> FuelOilForm()
                2 -> NaturalGasForm()
            }
        }
    }
}