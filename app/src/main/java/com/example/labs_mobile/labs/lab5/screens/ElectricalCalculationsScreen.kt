package com.example.labs_mobile.labs.lab5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab5.components.LossTab
import com.example.labs_mobile.labs.lab5.components.ReliabilityTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricalCalculationsScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Надійність", "Збитки")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Калькулятор") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTabIndex) {
                0 -> ReliabilityTab()
                1 -> LossTab()
            }
        }
    }
}
