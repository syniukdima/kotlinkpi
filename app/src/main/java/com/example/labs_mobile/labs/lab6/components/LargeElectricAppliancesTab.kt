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
fun LargeElectricAppliancesTab() {
    val scrollState = rememberScrollState()

    val loads = mutableListOf(
        ElectricalLoad("Зварювальний трансформатор", 0.92, 0.9, 0.38, 2, 100.0, 0.2, 3.0),
        ElectricalLoad("Сушильна шафа", 0.92, 0.9, 0.38, 2, 120.0, 0.8, null),
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