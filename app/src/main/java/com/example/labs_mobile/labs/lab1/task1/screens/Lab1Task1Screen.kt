package com.example.labs_mobile.labs.lab1.task1.screens
import com.example.labs_mobile.labs.lab1.task1.utils.FuelCalculator
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


@Composable
fun Lab1Task1Screen() {
    var hp by remember { mutableStateOf(3.8) }
    var cp by remember { mutableStateOf(62.4) }
    var sp by remember { mutableStateOf(3.6) }
    var np by remember { mutableStateOf(1.1) }
    var op by remember { mutableStateOf(4.3) }
    var wp by remember { mutableStateOf(6.0) }
    var ap by remember { mutableStateOf(18.8) }

    var dryMassResult by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var combustibleMassResult by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var lowerHeatingValueResult by remember { mutableStateOf(0.0) }
    var lowerHeatingValueResultDry by remember { mutableStateOf(0.0) }
    var lowerHeatingValueResultCombustible by remember { mutableStateOf(0.0) }

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
                text = "Fuel Composition Calculator",
                fontSize = 26.sp,
                color = Color(0xFF1E1E1E), // Darker text color
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            FuelInputField(value = hp, onValueChange = { hp = it }, label = "HP")
            FuelInputField(value = cp, onValueChange = { cp = it }, label = "CP")
            FuelInputField(value = sp, onValueChange = { sp = it }, label = "SP")
            FuelInputField(value = np, onValueChange = { np = it }, label = "NP")
            FuelInputField(value = op, onValueChange = { op = it }, label = "OP")
            FuelInputField(value = wp, onValueChange = { wp = it }, label = "WP")
            FuelInputField(value = ap, onValueChange = { ap = it }, label = "AP")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    dryMassResult = FuelCalculator.calculateDryMass(hp, cp, sp, np, op, wp, ap)
                    combustibleMassResult = FuelCalculator.calculateCombustibleMass(hp, cp, sp, np, op, wp, ap)
                    lowerHeatingValueResult = FuelCalculator.calculateLowerHeatingValueWorking(hp, cp, sp, op, wp)
                    lowerHeatingValueResultDry = FuelCalculator.calculateLowerHeatingValueDry(hp, cp, sp, op, wp)
                    lowerHeatingValueResultCombustible = FuelCalculator.calculateLowerHeatingValueCombustible(hp, cp, sp, op, wp, ap)
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
                ResultsDisplay(title = "Dry Mass Results", results = dryMassResult)
                ResultsDisplay(title = "Combustible Mass Results", results = combustibleMassResult)
                MainResultsDisplay(title = "Lower Heating Value (Working)", result = lowerHeatingValueResult)
                MainResultsDisplay(title = "Lower Heating Value (Dry)", result = lowerHeatingValueResultDry)
                MainResultsDisplay(title = "Lower Heating Value (Combustible)", result = lowerHeatingValueResultCombustible)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Formulas Used:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "1. Dry Mass (M_d) = 100 - WP",
                fontSize = 16.sp
            )
            Text(
                text = "2. Combustible Mass (M_c) = 100 - AP - WP",
                fontSize = 16.sp
            )
            Text(
                text = "3. Lower Heating Value (LHV_w) = ((339 * CP) + (1030 * HP) - (108.8 * (OP - SP)) - (25 * WP)) / 1000",
                fontSize = 16.sp
            )
            Text(
                text = "4. Lower Heating Value (LHV_d) = (LHV_w + 0.025 * WP) * K_RS",
                fontSize = 16.sp
            )
            Text(
                text = "5. Lower Heating Value (LHV_g) = (LHV_w + 0.025 * WP) * K_RG",
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

@Composable
fun MainResultsDisplay(title: String, result: Double) {
    Text(
        text = "$title = ${String.format("%.2f", result).toDouble()}",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Magenta,
        modifier = Modifier.fillMaxWidth()
    )
}