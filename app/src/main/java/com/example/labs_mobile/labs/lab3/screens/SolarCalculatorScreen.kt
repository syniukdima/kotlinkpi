package com.example.labs_mobile.labs.lab3.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.labs_mobile.labs.lab3.components.TextFieldComponent
import com.example.labs_mobile.labs.lab3.utils.SolarCalculator
import com.example.labs_mobile.labs.lab3.utils.SolarPowerStation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SolarCalculatorScreen() {
    var averagePower by remember { mutableStateOf(TextFieldValue("5.0")) }
    var pricePerKWh by remember { mutableStateOf(TextFieldValue("0.007")) }
    var initialSigma by remember { mutableStateOf(TextFieldValue("1.0")) }
    var improvedSigma by remember { mutableStateOf(TextFieldValue("0.25")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Solar Power Profit Calculator",
            fontSize = 24.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextFieldComponent(label = "Average Power (MW)", value = averagePower) { averagePower = it }
        TextFieldComponent(label = "Price per kWh (UAH)", value = pricePerKWh) { pricePerKWh = it }
        TextFieldComponent(label = "Initial Sigma (MW)", value = initialSigma) { initialSigma = it }
        TextFieldComponent(label = "Improved Sigma (MW)", value = improvedSigma) { improvedSigma = it }

        Button(
            onClick = {
                val station = SolarPowerStation(
                    averagePower = averagePower.text.toDouble(),
                    pricePerKWh = pricePerKWh.text.toDouble(),
                    initialSigma = initialSigma.text.toDouble(),
                    improvedSigma = improvedSigma.text.toDouble()
                )
                result = String.format("Profit: %.2f thousand UAH", SolarCalculator.calculateProfit(station))
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .background(Color(0xFF6200EE), RoundedCornerShape(8.dp))
        ) {
            Text("Calculate", color = Color.White)
        }

        Text(
            text = result,
            fontSize = 18.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
