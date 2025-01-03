package com.example.labs_mobile.labs.lab6.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab6.utils.ElectricalLoad
import com.example.labs_mobile.labs.lab6.utils.LoadCalculator


@Composable
fun ElectricalLoadCard(load: ElectricalLoad) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Прилад: ${load.name}", style = MaterialTheme.typography.bodyLarge)
            Text("Номінальна напруга: ${load.nominalVoltage} кВ")
            Text("Номінальна потужність: ${load.nominalPower} кВт")
            Text("Коефіцієнт використання: ${load.usageCoefficient}")
            Text("Коефіцієнт реактивної потужності: ${load.reactivePowerCoefficient}")

            val current = LoadCalculator.calculateLoadCurrent(load)
            Text("Розрахунковий струм: $current А")
        }
    }
}