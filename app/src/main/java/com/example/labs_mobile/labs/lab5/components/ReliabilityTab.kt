package com.example.labs_mobile.labs.lab5.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab5.utils.ReliabilityCalculator

@Composable
fun ReliabilityTab() {
    val scrollState = rememberScrollState()
    val reliabilityCalculator = ReliabilityCalculator()

    // Variables to store results
    var singleCircuitReliability by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var doubleCircuitReliability by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                singleCircuitReliability = reliabilityCalculator.calculateSingleCircuitReliability()
                doubleCircuitReliability = reliabilityCalculator.calculateDoubleCircuitReliability()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати надійність")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Надійність одноколової системи:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        singleCircuitReliability.forEach { (key, value) ->
            Text(
                "$key: ${String.format("%.4f", value)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Надійність двоколової системи: ${String.format("%.4f", doubleCircuitReliability)}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            "Отже, надійність двоколової системи електропередачі є значно вищою ніж одноколової.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}