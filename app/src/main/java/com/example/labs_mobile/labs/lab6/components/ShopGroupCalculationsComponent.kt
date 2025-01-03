package com.example.labs_mobile.labs.lab6.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab6.utils.LoadCalculator
import com.example.labs_mobile.labs.lab6.utils.ShopLoad

@Composable
fun ShopGroupCalculationsComponent() {
    val shopLoad = remember {
        ShopLoad(
            overallCount = 81,
            overallNominalPowerByCount = 2330.0,
            overallNominalPowerByCountByUsageCoefficient = 752.0,
            overallNominalPowerByCountByCoefficients = 657.0,
            overallSquaredNominalPowerByCount = 96399.0
        )
    }

    val fullUsageCoefficientLarge = LoadCalculator.calculateGroupUsageCoefficientFull(shopLoad)
    val effectiveLoadPowerFull = LoadCalculator.calculateEffectiveCountFull(shopLoad)
    val overallActivePower = LoadCalculator.calculateOverallActiveLoadPower(shopLoad)
    val overallReactivePower = LoadCalculator.calculateOverallReactiveLoadPower(shopLoad)
    val overallShopPower = LoadCalculator.calculateTotalPower(overallActivePower, overallReactivePower)
    val overallGroupCurrent = LoadCalculator.calculateGroupCurrent(overallActivePower, 0.38)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Результати розрахунків цеху",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ShopResultCard(
            title = "Коефіцієнт використання цеху (Крупні ЕП)",
            result = "$fullUsageCoefficientLarge"
        )

        ShopResultCard(
            title = "Ефективна кількість ЕП цеху",
            result = "$effectiveLoadPowerFull"
        )

        ShopResultCard(
            title = "Активне навантаження на шинах 0,38 кВ ТП",
            result = "$overallActivePower"
        )

        ShopResultCard(
            title = "Реактивне навантаження на шинах 0,38 кВ ТП",
            result = "$overallReactivePower"
        )

        ShopResultCard(
            title = "Повна потужність на шинах 0,38 кВ ТП",
            result = "$overallShopPower"
        )

        ShopResultCard(
            title = "Груповий струм на шинах 0,38 кВ ТП",
            result = "$overallGroupCurrent"
        )
    }
}