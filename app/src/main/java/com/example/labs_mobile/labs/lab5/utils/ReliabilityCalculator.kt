package com.example.labs_mobile.labs.lab5.utils



class ReliabilityCalculator {

    fun calculateSingleCircuitReliability(): Map<String, Double> {
        val failureRates = listOf(0.01, 0.07, 0.015, 0.02, 0.03 * 6)
        val omegaOs = failureRates.sum()

        val averageRepairTime = (0.01 * 30 + 0.07 * 10 + 0.015 * 100 + 0.02 * 15 + 0.18 * 2) / omegaOs
        val kAoc = (omegaOs * averageRepairTime) / 8760
        val kPos = 1.2 * 43 / 8760

        return mapOf(
            "omegaOs" to omegaOs,
            "averageRepairTime" to averageRepairTime,
            "kAoc" to kAoc,
            "kPos" to kPos
        )
    }

    fun calculateDoubleCircuitReliability(): Double {
        val singleCircuitReliability = calculateSingleCircuitReliability()
        val omegaDk = 2 * singleCircuitReliability["omegaOs"]!! *
                (singleCircuitReliability["kAoc"]!! + singleCircuitReliability["kPos"]!!)
        val omegaDs = omegaDk + 0.02

        return String.format("%.4f", omegaDs).toDouble()
    }
}

fun main() {
    val reliabilityCalculator = ReliabilityCalculator()

    val singleCircuitReliability = reliabilityCalculator.calculateSingleCircuitReliability()
    val doubleCircuitReliability = reliabilityCalculator.calculateDoubleCircuitReliability()

    println("Надійність одноколової системи: $singleCircuitReliability")
    println("Надійність двоколової системи: $doubleCircuitReliability")
}
