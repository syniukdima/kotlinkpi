package com.example.labs_mobile.labs.lab3.utils


data class SolarPowerStation(
    val averagePower: Double, // середньодобова потужність (МВт)
    val pricePerKWh: Double,  // вартість електроенергії (грн/кВт-год)
    val initialSigma: Double, // початкова похибка (МВт)
    val improvedSigma: Double // покращена похибка (МВт)
)

object SolarCalculator {

    private fun calculateEnergyPortion(sigma: Double, pc: Double, range: Double): Double {
        val lowerBound = pc - range
        val upperBound = pc + range
        val result = 0.5 * (erf((upperBound - pc) / (sigma * Math.sqrt(2.0))) - erf((lowerBound - pc) / (sigma * Math.sqrt(2.0))))
        return String.format("%.2f", result).toDouble()
    }

    fun calculateProfit(station: SolarPowerStation): Double {
        val hoursInDay = 24

        // Крок 1: Розрахунок для початкової системи прогнозу
        val deltaW1 = calculateEnergyPortion(station.initialSigma, station.averagePower, 0.25)
        println(deltaW1)
        val W1 = station.averagePower * hoursInDay * deltaW1 // Без небалансів
        val W2 = station.averagePower * hoursInDay * (1 - deltaW1) // З небалансами
        val P1 = W1 * station.pricePerKWh * 1000 // Прибуток за безбалансову енергію (грн)
        val S1 = W2 * station.pricePerKWh * 1000 // Штраф за енергію з небалансами (грн)
        val profitBefore = P1 - S1

        // Крок 2: Розрахунок для вдосконаленої системи прогнозу
        val deltaW2 = calculateEnergyPortion(station.improvedSigma, station.averagePower, 0.25)
        println(deltaW2)
        val W3 = station.averagePower * hoursInDay * deltaW2 // Без небалансів
        val W4 = station.averagePower * hoursInDay * (1 - deltaW2) // З небалансами
        val P2 = W3 * station.pricePerKWh * 1000 // Прибуток за безбалансову енергію (грн)
        val S2 = W4 * station.pricePerKWh * 1000 // Штраф за енергію з небалансами (грн)
        val profitAfter = P2 - S2

        return profitAfter
    }

    private fun erf(x: Double): Double {
        val t = 1.0 / (1.0 + 0.5 * Math.abs(x))
        val tau = t * Math.exp(-x * x - 1.26551223 +
                t * (1.00002368 +
                t * (0.37409196 +
                t * (0.09678418 +
                t * (-0.18628806 +
                t * (0.27886807 +
                t * (-1.13520398 +
                t * (1.48851587 +
                t * (-0.82215223 +
                t * 0.17087277)))))))))
        return if (x >= 0) 1 - tau else tau - 1
    }
}

fun main() {
    val station = SolarPowerStation(
        averagePower = 5.0,       // середньодобова потужність 5 МВт
        pricePerKWh = 7.0 / 1000, // ціна 7 грн за кВт-год
        initialSigma = 1.0,       // початкова похибка 1 МВт
        improvedSigma = 0.25      // вдосконалена похибка 0.25 МВт
    )

    val profit = SolarCalculator.calculateProfit(station)
    println("Прибуток після вдосконалення системи: $profit тис. грн")
}