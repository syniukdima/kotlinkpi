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
fun CoalForm() {
    var coalQr by remember { mutableStateOf("20.47") }
    var coalAvin by remember { mutableStateOf("0.8") }
    var coalAr by remember { mutableStateOf("25.20") }
    var coalGvin by remember { mutableStateOf("1.5") }
    var coalNuz by remember { mutableStateOf("0.985") }
    var coalMass by remember { mutableStateOf("1096363.0") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = "Калькулятор викидів вугілля", style = MaterialTheme.typography.headlineMedium)

        InputField("Qr вугілля (МДж/кг)", coalQr) { coalQr = it }
        InputField("Avin вугілля", coalAvin) { coalAvin = it }
        InputField("Ar вугілля (%)", coalAr) { coalAr = it }
        InputField("Gvin вугілля (%)", coalGvin) { coalGvin = it }
        InputField("Nuz вугілля", coalNuz) { coalNuz = it }
        InputField("Маса вугілля (т)", coalMass) { coalMass = it }

        Button(
            onClick = {
                result = EmissionCalculator.calculateCoalEmissions(
                    coalQr.toDouble(),
                    coalAvin.toDouble(),
                    coalAr.toDouble(),
                    coalGvin.toDouble(),
                    coalNuz.toDouble(),
                    coalMass.toDouble()
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Валові викиди вугілля: ${String.format("%.2f", result)} т",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
