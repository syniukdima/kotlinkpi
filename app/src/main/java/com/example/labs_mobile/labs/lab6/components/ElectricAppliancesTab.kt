package com.example.labs_mobile.labs.lab6.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab6.utils.ElectricalLoad

@Composable
fun ElectricAppliancesTab() {
    val scrollState = rememberScrollState()

    val loads = mutableListOf(
        ElectricalLoad("Шліфувальний верстат", 0.92, 0.9, 0.38, 4, 20.0, 0.15, 1.33),
        ElectricalLoad("Свердлильний верстат", 0.92, 0.9, 0.38, 2, 14.0, 0.12, 1.0),
        ElectricalLoad("Фугувальний верстат", 0.92, 0.9, 0.38, 4, 42.0, 0.15, 1.33),
        ElectricalLoad("Циркулярна пила", 0.92, 0.9, 0.38, 1, 36.0, 0.3, 1.52),
        ElectricalLoad("Прес", 0.92, 0.9, 0.38, 1, 20.0, 0.5, 0.75),
        ElectricalLoad("Полірувальний верстат", 0.92, 0.9, 0.38, 1, 40.0, 0.2, 1.0),
        ElectricalLoad("Фрезерний верстат", 0.92, 0.9, 0.38, 2, 32.0, 0.2, 1.0),
        ElectricalLoad("Вентилятор", 0.92, 0.9, 0.38, 1, 20.0, 0.65, 0.75)
    )

    Column(
    modifier = Modifier
    .verticalScroll(scrollState)
    .fillMaxSize()
    .padding(16.dp),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Групи приладів", style = MaterialTheme.typography.headlineSmall)

        loads.forEach { load ->
            ElectricalLoadCard(load)
        }

        Spacer(modifier = Modifier.height(16.dp))

        GroupCalculationsComponent(loads)
    }
}