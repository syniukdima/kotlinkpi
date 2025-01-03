package com.example.labs_mobile.labs.lab1.task1.utils


object FuelCalculator {
    private fun transitionCoefficientWorkingToDry(wp: Double): Double {
        return 100 / (100 - wp)
    }

    private fun transitionCoefficientWorkingToCombustible(wp: Double, ap: Double): Double {
        return 100 / (100 - wp - ap)
    }

    fun calculateDryMass(hp: Double, cp: Double, sp: Double, np: Double, op: Double, wp: Double, ap: Double): Map<String, Double> {
        val dryMassFactor = transitionCoefficientWorkingToDry(wp)
        return mapOf(
            "H^С" to hp * dryMassFactor,
            "C^С" to cp * dryMassFactor,
            "S^С" to sp * dryMassFactor,
            "N^С" to np * dryMassFactor,
            "O^С" to op * dryMassFactor,
            "A^С" to ap * dryMassFactor
        )
    }

    fun calculateCombustibleMass(hp: Double, cp: Double, sp: Double, np: Double, op: Double, wp: Double, ap: Double): Map<String, Double> {
        val combustibleMassFactor = transitionCoefficientWorkingToCombustible(wp, ap)
        return mapOf(
            "H^Г" to hp * combustibleMassFactor,
            "C^Г" to cp * combustibleMassFactor,
            "S^Г" to sp * combustibleMassFactor,
            "N^Г" to np * combustibleMassFactor,
            "O^Г" to op * combustibleMassFactor
        )
    }

    fun calculateLowerHeatingValueWorking(hp: Double, cp: Double, sp: Double, op: Double, wp: Double): Double {
        return ((339 * cp) + (1030 * hp) - 108.8 * (op - sp) - 25 * wp) / 1000
    }

    // Convert lower heating value to dry mass
    fun calculateLowerHeatingValueDry(hp: Double, cp: Double, sp: Double, op: Double, wp: Double): Double {
        val K_RS = transitionCoefficientWorkingToDry(wp)
        return (calculateLowerHeatingValueWorking(hp, cp, sp, op, wp) + 0.025 * wp) * K_RS
    }

    // Convert lower heating value to combustible mass
    fun calculateLowerHeatingValueCombustible(hp: Double, cp: Double, sp: Double, op: Double, wp: Double, ap: Double): Double {
        val K_RG = transitionCoefficientWorkingToCombustible(wp, ap)
        return (calculateLowerHeatingValueWorking(hp, cp, sp, op, wp) + 0.025 * wp) * K_RG
    }

}

fun main() {
    val hp = 1.9
    val cp = 21.1
    val sp = 2.6
    val np = 0.2
    val op = 7.1
    val wp = 53.0
    val ap = 14.1

    val dryMass = FuelCalculator.calculateDryMass(hp, cp, sp, np, op, wp, ap)
    val combustibleMass = FuelCalculator.calculateCombustibleMass(hp, cp, sp, np, op, wp, ap)
    val lowerHeatingValueWorking = FuelCalculator.calculateLowerHeatingValueWorking(hp, cp, sp, op, wp)
    val lowerHeatingValueDry = FuelCalculator.calculateLowerHeatingValueDry(hp, cp, sp, op, wp)
    val lowerHeatingValueCombustible = FuelCalculator.calculateLowerHeatingValueCombustible(hp, cp, sp, op, wp, ap)

    println("Dry mass: $dryMass")
    println("Combustible mass: $combustibleMass")
    println("Lower heating value (Working): $lowerHeatingValueWorking")
    println("Lower heating value (Dry): $lowerHeatingValueDry")
    println("Lower heating value (Combustible): $lowerHeatingValueCombustible")
}
