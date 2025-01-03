package com.example.labs_mobile.labs.lab6.utils

import kotlin.math.sqrt
import kotlin.math.pow


data class ElectricalLoad(
    val name: String,
    val nominalEfficiency: Double,
    val powerFactor: Double,
    val nominalVoltage: Double,
    val count: Int,
    val nominalPower: Double,
    val usageCoefficient: Double,
    val reactivePowerCoefficient: Double?
)

data class ShopLoad(
    val overallCount: Int,
    val overallNominalPowerByCount: Double,
    val overallNominalPowerByCountByUsageCoefficient: Double,
    val overallNominalPowerByCountByCoefficients: Double,
    val overallSquaredNominalPowerByCount: Double,
)

object LoadCalculator {

    fun calculateLoadCurrent(load: ElectricalLoad): Double {
        val totalPower = load.count * load.nominalPower
        return totalPower / (sqrt(3.0) * load.nominalVoltage * load.powerFactor * load.nominalEfficiency)
    }

    fun calculateGroupUsageCoefficient(loads: List<ElectricalLoad>): Double {
        val totalPower = loads.sumOf { it.count * it.nominalPower * it.usageCoefficient }
        val totalNominalPower = loads.sumOf { it.count * it.nominalPower }
        return String.format("%.4f", totalPower / totalNominalPower).toDouble()
    }

    fun calculateEffectiveCount(loads: List<ElectricalLoad>): Double {
        val sumNominalPowerSquared = loads.sumOf { (it.count * it.nominalPower) }
        val totalNominalPower = loads.sumOf { it.count * it.nominalPower.pow(2) }
        return String.format("%.4f", sumNominalPowerSquared.pow(2) / totalNominalPower).toDouble()
    }

    fun calculateActiveLoadPower(groupUsageCoefficient: Double, effectiveLoadPower: Double): Double {
        val activePowerCoefficient = 1.25
        val activeLoadPower = activePowerCoefficient * groupUsageCoefficient * effectiveLoadPower

        return String.format("%.2f", activeLoadPower).toDouble()
    }

    fun calculateTotalReactiveLoadPower(loads: List<ElectricalLoad>): Double {
        val totalReactivePower = loads.sumOf {
            if (it.reactivePowerCoefficient == null || it.reactivePowerCoefficient == 0.0) {
                0.0
            } else {
                it.count * it.usageCoefficient * it.nominalPower * it.reactivePowerCoefficient
            }
        }
        return String.format("%.3f", totalReactivePower).toDouble()
    }

    fun calculateTotalPower(activePower: Double, reactivePower: Double): Double {
        val totalPower = sqrt(activePower.pow(2) + reactivePower.pow(2))

        return String.format("%.4f", totalPower).toDouble()
    }

    fun calculateGroupCurrent(totalPower: Double, nominalVoltage: Double): Double {
        val groupCurrent = totalPower / nominalVoltage

        return String.format("%.2f", groupCurrent).toDouble()
    }

    fun calculateGroupUsageCoefficientFull(load: ShopLoad): Double {
        val result = load.overallNominalPowerByCountByUsageCoefficient / load.overallNominalPowerByCount
        return String.format("%.2f", result).toDouble()
    }

    fun calculateEffectiveCountFull(load: ShopLoad): Int {
        val result = load.overallNominalPowerByCount.pow(2) / load.overallSquaredNominalPowerByCount
        return result.toInt()
    }

    fun calculateOverallActiveLoadPower(load: ShopLoad): Double {
        val activePowerCoefficient = 0.7
        val activeLoadPower = activePowerCoefficient * load.overallNominalPowerByCountByUsageCoefficient

        return String.format("%.1f", activeLoadPower).toDouble()
    }

    fun calculateOverallReactiveLoadPower(load: ShopLoad): Double {
        val activePowerCoefficient = 0.7
        val activeLoadPower = activePowerCoefficient * load.overallNominalPowerByCountByCoefficients

        return String.format("%.1f", activeLoadPower).toDouble()
    }
}

fun main() {
    val loads = mutableListOf(
        ElectricalLoad("Шліфувальний верстат", 0.92, 0.9, 0.38, 4, 20.0, 0.15, 1.33),
        ElectricalLoad("Свердлильний верстат", 0.92, 0.9, 0.38, 2, 14.0, 0.12, 1.0),
        ElectricalLoad("Фугувальний верстат", 0.92, 0.9, 0.38, 4, 42.0, 0.15, 1.33),
        ElectricalLoad("Циркулярна пила", 0.92, 0.9, 0.38, 1, 36.0, 0.3, 1.52),
        ElectricalLoad("Прес", 0.92, 0.9, 0.38, 1, 20.0, 0.5, 0.75),
        ElectricalLoad("Полірувальний верстат", 0.92, 0.9, 0.38, 1, 40.0, 0.2, 1.0),
        ElectricalLoad("Фрезерний верстат", 0.92, 0.9, 0.38, 2, 32.0, 0.2, 1.0),
        ElectricalLoad("Вентилятор", 0.92, 0.9, 0.38, 1, 20.0, 0.65, 0.75)
    )

    for (load in loads) {
        val current = LoadCalculator.calculateLoadCurrent(load)
        println("Розрахунковий струм для ${load.name}: $current А")
    }

    val groupUsageCoefficient = LoadCalculator.calculateGroupUsageCoefficient(loads)
    val effectiveCount = LoadCalculator.calculateEffectiveCount(loads)

    println("Груповий коефіцієнт використання: $groupUsageCoefficient")
    println("Ефективна кількість ЕП: $effectiveCount")


    val effectiveLoadPower = loads.sumOf { it.nominalPower * it.count }
    val activePower = LoadCalculator.calculateActiveLoadPower(groupUsageCoefficient, effectiveLoadPower)
    val reactivePower = LoadCalculator.calculateTotalReactiveLoadPower(loads)
    val totalPower = LoadCalculator.calculateTotalPower(activePower, reactivePower)
    val groupCurrent = LoadCalculator.calculateGroupCurrent(activePower, loads[0].nominalVoltage)

    println("Загальне активне навантаження: $activePower кВт")
    println("Загальне реактивне навантаження: $reactivePower квар")
    println("Загальна потужність: $totalPower кВА")
    println("Груповий струм: $groupCurrent А")


    val largeLoads = mutableListOf(
        ElectricalLoad("Зварювальний трансформатор", 0.92, 0.9, 0.38, 2, 100.0, 0.2, 3.0),
        ElectricalLoad("Сушильна шафа", 0.92, 0.9, 0.38, 2, 120.0, 0.8, null),
    )

    for (load in largeLoads) {
        val current = LoadCalculator.calculateLoadCurrent(load)
        println("Розрахунковий струм для ${load.name}: $current А")
    }

    val groupUsageCoefficientLarge = LoadCalculator.calculateGroupUsageCoefficient(largeLoads)
    val effectiveCountLarge = LoadCalculator.calculateEffectiveCount(largeLoads)
    println("Груповий коефіцієнт використання (Крупні ЕП): $groupUsageCoefficientLarge")
    println("Ефективна кількість ЕП (Крупні ЕП): $effectiveCountLarge")


    val effectiveLoadPowerLarge = largeLoads.sumOf { it.nominalPower * it.count }
    val activePowerLarge = LoadCalculator.calculateActiveLoadPower(groupUsageCoefficientLarge, effectiveLoadPowerLarge)
    val reactivePowerLarge = LoadCalculator.calculateTotalReactiveLoadPower(largeLoads)
    val totalPowerLarge = LoadCalculator.calculateTotalPower(activePowerLarge, reactivePowerLarge)
    val groupCurrentLarge = LoadCalculator.calculateGroupCurrent(activePowerLarge, largeLoads[0].nominalVoltage)

    println("Загальне активне навантаження (Крупні ЕП): $activePowerLarge кВт")
    println("Загальне реактивне навантаження (Крупні ЕП): $reactivePowerLarge квар")
    println("Загальна потужність (Крупні ЕП): $totalPowerLarge кВА")
    println("Груповий струм (Крупні ЕП): $groupCurrentLarge А")


    val shopLoad = ShopLoad(
        overallCount = 81,
        overallNominalPowerByCount = 2330.0,
        overallNominalPowerByCountByUsageCoefficient = 752.0,
        overallNominalPowerByCountByCoefficients = 657.0,
        overallSquaredNominalPowerByCount = 96399.0
    )

    val fullUsageCoefficientLarge = LoadCalculator.calculateGroupUsageCoefficientFull(shopLoad)
    val effectiveLoadPowerFull = LoadCalculator.calculateEffectiveCountFull(shopLoad)
    val overallActivePower = LoadCalculator.calculateOverallActiveLoadPower(shopLoad)
    val overallReactivePower = LoadCalculator.calculateOverallReactiveLoadPower(shopLoad)
    val overallShopPower = LoadCalculator.calculateTotalPower(overallActivePower, overallReactivePower)
    val overallGroupCurrent = LoadCalculator.calculateGroupCurrent(overallActivePower, 0.38)

    println("Коефіцієнти використання цеху в цілому (Крупні ЕП): $fullUsageCoefficientLarge")
    println("Ефективна кількість ЕП цеху в цілому: $effectiveLoadPowerFull")
    println("Розрахункове активне навантаження на шинах 0,38 кВ ТП: $overallActivePower")
    println("Розрахункове реактивне навантаження на шинах 0,38 кВ ТП: $overallReactivePower")
    println("Повна потужність на шинах 0,38 кВ ТП: $overallShopPower")
    println("Розрахунковий груповий струм на шинах 0,38 кВ ТП: $overallGroupCurrent")
}
