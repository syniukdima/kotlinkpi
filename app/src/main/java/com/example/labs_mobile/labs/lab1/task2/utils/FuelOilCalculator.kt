package com.example.labs_mobile.labs.lab1.task2.utils


object FuelOilCalculator {
    fun calculateWorkingMass(
        carbon: Double, hydrogen: Double, sulfur: Double, oxygen: Double,
        moisture: Double, ash: Double, vanadium: Double
    ): Map<String, Double> {
        val factor = (100 - moisture - ash) / 100
        return mapOf(
            "C^Р" to carbon * factor,
            "H^Р" to hydrogen * factor,
            "S^Р" to sulfur * factor,
            "O^Р" to oxygen * factor,
            "A^Р" to ash,
            "V^Р" to vanadium * (100 - moisture) / 100
        )
    }

    fun calculateLowerHeatingValueWorking(
        lowerHeatingValueCombustible: Double, moisture: Double, ash: Double
    ): Double {
        val factor = (100 - moisture - ash) / 100
        return lowerHeatingValueCombustible * factor - 0.025 * moisture
    }
}

fun main() {
    val carbon = 85.50
    val hydrogen = 11.20
    val sulfur = 2.50
    val oxygen = 0.80
    val moisture = 2.00
    val ash = 0.15
    val vanadium = 333.3
    val lowerHeatingValueCombustible = 40.40 // МДж/кг

    val workingMass = FuelOilCalculator.calculateWorkingMass(carbon, hydrogen, sulfur, oxygen, moisture, ash, vanadium)

    val lowerHeatingValueWorking = FuelOilCalculator.calculateLowerHeatingValueWorking(
        lowerHeatingValueCombustible, moisture, ash
    )

    println("Склад робочої маси мазуту: $workingMass")
    println("Нижча теплота згоряння мазуту на робочу масу: $lowerHeatingValueWorking МДж/кг")
}
