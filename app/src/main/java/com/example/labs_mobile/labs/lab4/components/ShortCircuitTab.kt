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
fun ShortCircuitTab() {
    var sK by remember { mutableStateOf("200.0") }
    var uNom by remember { mutableStateOf("10.5") }
    var result by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Розрахунок короткого замикання", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = sK,
            onValueChange = { sK = it },
            label = { Text("S_k (кВар)") }
        )
        TextField(
            value = uNom,
            onValueChange = { uNom = it },
            label = { Text("U_nom (кВ)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val results = ShortCircuitCalculator().calculateShortCircuitCurrent(sK.toDouble(), uNom.toDouble())
            result = """
                Опір короткого замикання на 10кВ, Хс: ${String.format("%.2f", results["X_c"])} 
                Опір трансформатора, Xt: ${String.format("%.2f", results["X_t"])} 
                Сумарний опір для точки замикання, Xета: ${String.format("%.2f", results["X_total"])} 
                Початкове діюче значення струму трифазного КЗ, Ip0: ${String.format("%.2f", results["I_p0"])} 
                Базисний струм для низької напруги, Iб: ${String.format("%.2f", results["I_base"])} 
                Приведений опір короткого замикання, Xсб: ${String.format("%.2f", results["X_c_base"])} 
                Приведений опір трансформатора, Xтб: ${String.format("%.2f", results["X_t_base"])} 
            """.trimIndent()
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(result, style = MaterialTheme.typography.bodyLarge)
    }
}
