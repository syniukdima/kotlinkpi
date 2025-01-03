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
fun GroupCalculationsComponent(loads: List<ElectricalLoad>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Групові розрахунки", style = MaterialTheme.typography.headlineSmall)

            val groupUsageCoefficient = LoadCalculator.calculateGroupUsageCoefficient(loads)
            val effectiveCount = LoadCalculator.calculateEffectiveCount(loads)
            val effectiveLoadPower = loads.sumOf { it.nominalPower * it.count }
            val activePower = LoadCalculator.calculateActiveLoadPower(groupUsageCoefficient, effectiveLoadPower)
            val reactivePower = LoadCalculator.calculateTotalReactiveLoadPower(loads)
            val totalPower = LoadCalculator.calculateTotalPower(activePower, reactivePower)
            val groupCurrent = LoadCalculator.calculateGroupCurrent(activePower, loads[0].nominalVoltage)

            Text("Груповий коефіцієнт використання: $groupUsageCoefficient")
            Text("Ефективна кількість приладів: $effectiveCount")
            Text("Загальне активне навантаження: $activePower кВт")
            Text("Загальне реактивне навантаження: $reactivePower квар")
            Text("Загальна потужність: $totalPower кВА")
            Text("Груповий струм: $groupCurrent А")
        }
    }
}