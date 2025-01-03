package com.example.labs_mobile.labs.lab5.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab5.utils.LossCalculator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LossTab() {
    val scrollState = rememberScrollState()
    val lossCalculator = LossCalculator()
    var totalLosses by remember { mutableStateOf(0.0) }

    // Поля введення для калькулятора збитків
    var emergencyRate by remember { mutableStateOf("23.6") }
    var plannedRate by remember { mutableStateOf("17.6") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = emergencyRate,
            onValueChange = { emergencyRate = it },
            label = { Text("Тариф за аварійні вимкнення (грн./кВт-год.)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = plannedRate,
            onValueChange = { plannedRate = it },
            label = { Text("Тариф за планові вимкнення (грн./кВт-год.)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val emergencyRateValue = emergencyRate.toDoubleOrNull() ?: 0.0
                val plannedRateValue = plannedRate.toDoubleOrNull() ?: 0.0
                totalLosses = lossCalculator.calculateTotalLosses(emergencyRateValue, plannedRateValue)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати збитки")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Загальні збитки: %.2f грн.".format(totalLosses), style = MaterialTheme.typography.bodyMedium)
    }
}
