package com.example.labs_mobile.labs.lab4.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab4.utils.ShortCircuitCalculator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HPnEMShortCircuitTab() {
    var uVn by remember { mutableStateOf("115.0") }
    var rSn by remember { mutableStateOf("10.65") }
    var xSn by remember { mutableStateOf("24.02") }
    var rSmin by remember { mutableStateOf("34.88") }
    var xSmin by remember { mutableStateOf("65.68") }
    var result by remember { mutableStateOf("") }

    var scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp).verticalScroll(scrollState)
    ) {
        Text("Розрахунок КЗ для ХПнЕМ", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = uVn,
            onValueChange = { uVn = it },
            label = { Text("Uvn (кВ)") }
        )
        TextField(
            value = rSn,
            onValueChange = { rSn = it },
            label = { Text("Rsn (Ом)") }
        )
        TextField(
            value = xSn,
            onValueChange = { xSn = it },
            label = { Text("Xsn (Ом)") }
        )
        TextField(
            value = rSmin,
            onValueChange = { rSmin = it },
            label = { Text("Rsmin (Ом)") }
        )
        TextField(
            value = xSmin,
            onValueChange = { xSmin = it },
            label = { Text("Xsmin (Ом)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val results = ShortCircuitCalculator().calculateHPnEMShortCircuitCurrent(
                Uvn = uVn.toDouble(),
                Rsn = rSn.toDouble(),
                Xsn = xSn.toDouble(),
                Rsmin = rSmin.toDouble(),
                Xsmin = xSmin.toDouble()
            )
            result = """
                Реактивний опір трансформатора, Xт: ${String.format("%.2f", results["Xt"])} 
                Загальний опір Zш: ${String.format("%.2f", results["Zsh"])} 
                Струм трифазного КЗ у нормальному режимі: ${String.format("%.2f", results["Ish3Normal"])} 
                Струм двофазного КЗ у нормальному режимі: ${String.format("%.2f", results["Ish2Normal"])} 
                Струм трифазного КЗ у мінімальному режимі: ${String.format("%.2f", results["Ish3Min"])} 
                Струм двофазного КЗ у мінімальному режимі: ${String.format("%.2f", results["Ish2Min"])} 
                Коефіцієнт приведення, Kпр: ${String.format("%.2f", results["Kpr"])} 
                Загальний опір на шинах 10 кВ в нормальному режимі, ZшН: ${String.format("%.2f", results["ZshN"])} 
                Струм трифазного КЗ на шинах 10 кВ в нормальному режимі, Iш.н(3): ${String.format("%.2f", results["IshN3"])} 
                Струм двофазного КЗ на шинах 10 кВ в нормальному режимі, Iш.н(2): ${String.format("%.2f", results["IshN2"])} 
                Загальний опір на шинах 10 кВ в мінімальному режимі, ZшН min: ${String.format("%.2f", results["ZshNMin"])} 
                Струм трифазного КЗ на шинах 10 кВ в мінімальному режимі, Iш.н min(3): ${String.format("%.2f", results["IshNMin3"])} 
                Струм двофазного КЗ на шинах 10 кВ в мінімальному режимі, Iш.н min(2): ${String.format("%.2f", results["IshNMin2"])} 
                Резистанс лінії, Rл: ${String.format("%.2f", results["Rl"])} 
                Реактанс лінії, Xл: ${String.format("%.2f", results["Xl"])} 
                Повний опір в точці 10 в нормальному режимі, Zета Н: ${String.format("%.2f", results["ZetaN"])} 
                Струм трифазного КЗ в точці 10 в нормальному режимі, Iл.н (3): ${String.format("%.2f", results["IlN3"])} 
                Струм двофазного КЗ в точці 10 в нормальному режимі, Iл.н (2): ${String.format("%.2f", results["IlN2"])} 
                Повний опір в точці 10 в мінімальному режимі, Zета Н min: ${String.format("%.2f", results["ZetaNMin"])} 
                Струм трифазного КЗ в точці 10 в мінімальному режимі, Iл.н min (3): ${String.format("%.2f", results["IlNMin3"])} 
                Струм двофазного КЗ в точці 10 в мінімальному режимі, Iл.н min (2): ${String.format("%.2f", results["IlNMin2"])} 
            """.trimIndent()
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(result, style = MaterialTheme.typography.bodyLarge)
    }
}
