package com.example.labs_mobile.labs.lab5.utils

import kotlin.math.round


class LossCalculator {

    fun calculateEmergencyLosses(): Double {
        val omega = 0.01
        val tV = 45e-3 // в роках
        val Pm = 5.12e3 // потужність
        val Tm = 6451 // година

        val expectedShortage = omega * tV * Pm * Tm
        return round(expectedShortage / 100) * 100  // rounding
    }

    fun calculatePlannedLosses(): Double {
        val kP = 4e-3
        val Pm = 5.12e3 // потужність
        val Tm = 6451 // година

        return round(kP * Pm * Tm / 100) * 100  // rounding
    }

    fun calculateTotalLosses(emergencyRate: Double, plannedRate: Double): Double {
        val M_WnedA = calculateEmergencyLosses()
        val M_WnedP = calculatePlannedLosses()
        return (emergencyRate * M_WnedA) + (plannedRate * M_WnedP)
    }
}

fun main() {
    val lossCalculator = LossCalculator()

    val emergencyRate = 23.6 // грн./кВт-год.
    val plannedRate = 17.6 // грн./кВт-год.
    val totalLosses = lossCalculator.calculateTotalLosses(emergencyRate, plannedRate)

    println("Загальні збитки від перерв електропостачання: $totalLosses грн.")
}
