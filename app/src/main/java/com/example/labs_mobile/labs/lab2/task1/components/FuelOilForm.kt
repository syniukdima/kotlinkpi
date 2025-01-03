package com.example.labs_mobile.labs.lab2.task1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab2.task1.utils.EmissionCalculator

@Composable
fun FuelOilForm() {
    var fuelOilQr by remember { mutableStateOf("39.48") }
    var fuelOilAvin by remember { mutableStateOf("1.0") }
    var fuelOilAr by remember { mutableStateOf("0.15") }
    var fuelOilGvin by remember { mutableStateOf("0.0") }
    var fuelOilNuz by remember { mutableStateOf("0.985") }
    var fuelOilMass by remember { mutableStateOf("70945.0") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = "Калькулятор викидів мазуту", style = MaterialTheme.typography.headlineMedium)

        InputField("Qr мазуту (МДж/кг)", fuelOilQr) { fuelOilQr = it }
        InputField("Avin мазуту", fuelOilAvin) { fuelOilAvin = it }
        InputField("Ar мазуту (%)", fuelOilAr) { fuelOilAr = it }
        InputField("Gvin мазуту (%)", fuelOilGvin) { fuelOilGvin = it }
        InputField("Nuz мазуту", fuelOilNuz) { fuelOilNuz = it }
        InputField("Маса мазуту (т)", fuelOilMass) { fuelOilMass = it }

        Button(
            onClick = {
                result = EmissionCalculator.calculateFuelOilEmissions(
                    fuelOilQr.toDouble(),
                    fuelOilAvin.toDouble(),
                    fuelOilAr.toDouble(),
                    fuelOilGvin.toDouble(),
                    fuelOilNuz.toDouble(),
                    fuelOilMass.toDouble()
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Валові викиди мазуту: ${String.format("%.2f", result)} т",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
