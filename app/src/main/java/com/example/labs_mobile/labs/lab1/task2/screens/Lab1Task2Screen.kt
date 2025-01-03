package com.example.labs_mobile.labs.lab1.task2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import com.example.labs_mobile.labs.lab1.task2.utils.FuelOilCalculator


@Composable
fun Lab1Task2Screen() {
    var carbon by remember { mutableStateOf(85.50) }
    var hydrogen by remember { mutableStateOf(11.20) }
    var sulfur by remember { mutableStateOf(2.50) }
    var oxygen by remember { mutableStateOf(0.80) }
    var moisture by remember { mutableStateOf(2.00) }
    var ash by remember { mutableStateOf(0.15) }
    var vanadium by remember { mutableStateOf(333.3) }
    var lowerHeatingValueCombustible by remember { mutableStateOf(40.40) }

    var workingMassResult by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var lowerHeatingValueWorkingResult by remember { mutableStateOf(0.0) }

    var error by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Fuel Oil Composition Calculator",
                fontSize = 26.sp,
                color = Color(0xFF1E1E1E),
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            FuelInputField(value = carbon, onValueChange = { carbon = it }, label = "Carbon (C%)")
            FuelInputField(value = hydrogen, onValueChange = { hydrogen = it }, label = "Hydrogen (H%)")
            FuelInputField(value = sulfur, onValueChange = { sulfur = it }, label = "Sulfur (S%)")
            FuelInputField(value = oxygen, onValueChange = { oxygen = it }, label = "Oxygen (O%)")
            FuelInputField(value = moisture, onValueChange = { moisture = it }, label = "Moisture (W%)")
            FuelInputField(value = ash, onValueChange = { ash = it }, label = "Ash (A%)")
            FuelInputField(value = vanadium, onValueChange = { vanadium = it }, label = "Vanadium (V)")
            FuelInputField(value = lowerHeatingValueCombustible, onValueChange = { lowerHeatingValueCombustible = it }, label = "Lower Heating Value Combustible (MJ/kg)")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        workingMassResult = FuelOilCalculator.calculateWorkingMass(carbon, hydrogen, sulfur, oxygen, moisture, ash, vanadium)
                        lowerHeatingValueWorkingResult = FuelOilCalculator.calculateLowerHeatingValueWorking(lowerHeatingValueCombustible, moisture, ash)
                        error = null
                    } catch (e: Exception) {
                        error = "Calculation error: ${e.message}"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Text("Calculate", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (error != null) {
                Text(
                    text = error!!,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                ResultsDisplay(title = "Working Mass Results", results = workingMassResult)
                Text(
                    text = "Lower Heating Value Working = ${String.format("%.2f", lowerHeatingValueWorkingResult)} MJ/kg",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Magenta,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Formulas Used:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "1. Working Mass (C^P, H^P, S^P, O^P, A^P, V^P)",
                fontSize = 16.sp
            )
            Text(
                text = "2. Lower Heating Value (LHV_w) = LHV_g * (100 - W% - A%) / 100 - 0.025 * W%",
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelInputField(value: Double, onValueChange: (Double) -> Unit, label: String) {
    var inputValue by remember { mutableStateOf(value.toString()) }

    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = inputValue,
            onValueChange = {
                if (it.all { char -> char.isDigit() || char == '.' }) {
                    inputValue = it
                    it.toDoubleOrNull()?.let { it1 -> onValueChange(it1) }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun ResultsDisplay(title: String, results: Map<String, Double>) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta
        )
        results.forEach { (key, value) ->
            Text(
                text = "$key = ${"%.2f".format(value)}",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
