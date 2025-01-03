package com.example.labs_mobile.labs.lab4.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab4.utils.ShortCircuitCalculator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CableSelectionTab() {
    var ik by remember { mutableStateOf("2500.0") }
    var tF by remember { mutableStateOf("2.5") }
    var sm by remember { mutableStateOf("1300.0") }
    var unom by remember { mutableStateOf("10.0") }
    var tm by remember { mutableStateOf("4000.0") }
    var result by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text("Вибір кабелю", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = ik,
            onValueChange = { ik = it },
            label = { Text("Ik (A)") }
        )
        TextField(
            value = tF,
            onValueChange = { tF = it },
            label = { Text("tФ") }
        )
        TextField(
            value = sm,
            onValueChange = { sm = it },
            label = { Text("Sm (mm²)") }
        )
        TextField(
            value = unom,
            onValueChange = { unom = it },
            label = { Text("Uном") }
        )
        TextField(
            value = tm,
            onValueChange = { tm = it },
            label = { Text("Tм") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result = with(ShortCircuitCalculator()) {
                val I_m = (sm.toDouble() / 2) / (1.732 * unom.toDouble())
                val I_mPa = 2 * I_m
                val jEk = 1.4
                val Sek = I_m / jEk
                val sMin = (ik.toDouble() * Math.sqrt(tF.toDouble())) / 92.0
                val finalSek = if (sMin >= 50) sMin else 50.0

                """
                Розрахунковий струм: ${String.format("%.2f", I_m)} A
                Післяаварійний струм: ${String.format("%.2f", I_mPa)} A
                Економічний переріз кабелю: ${String.format("%.2f", Sek)} мм²
                Мінімальний переріз: ${String.format("%.2f", sMin)} мм²
                Вибраний переріз: ${String.format("%.2f", finalSek)} мм²
                """.trimIndent()
            }
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(result, style = MaterialTheme.typography.bodyLarge)
    }
}
