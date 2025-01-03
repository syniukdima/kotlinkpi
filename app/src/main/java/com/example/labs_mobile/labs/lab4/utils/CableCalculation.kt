package com.example.labs_mobile.labs.lab4.utils

import kotlin.math.round
import kotlin.math.sqrt

class ShortCircuitCalculator {

    fun calculateCableSelection(Ik: Double, tF: Double, Sm: Double, Unom: Double, Tm: Double): Map<String, Double> {
        val Im = round((Sm / 2) / (sqrt(3.0) * Unom) * 100) / 100
        val ImPa = round(2 * Im * 100) / 100

        val jEk = 1.4
        val Sek = round((Im / jEk) * 100) / 100

        val Ct = 92.0
        val sMin = round((Ik * sqrt(tF)) / Ct * 100) / 100
        val finalSek = if (sMin >= 50) sMin else 50.0

        return mapOf(
            "Im" to Im,
            "ImPa" to ImPa,
            "Sek" to Sek,
            "sMin" to sMin,
            "finalSek" to finalSek
        )
    }

    fun calculateShortCircuitCurrent(S_k: Double, U_nom: Double): Map<String, Double> {
        val X_c = round((U_nom * U_nom) / S_k * 100) / 100

        val U_k = 10.5
        val S_nom_t = 6.3
        val X_t = round((U_k / 100) * ((U_nom * U_nom) / S_nom_t) * 100) / 100

        val X_total = round((X_c + X_t) * 100) / 100
        val I_p0 = round((U_nom / (sqrt(3.0) * X_total)) * 100) / 100

        val S_base = 1000.0
        val U_base = 10.0
        val I_base = round((S_base / (sqrt(3.0) * U_base)) * 100) / 100

        val X_c_base = round((S_base / S_k) * 100) / 100
        val X_t_base = round((U_k / 100) * S_base / S_nom_t * 100) / 100

        return mapOf(
            "X_c" to X_c,
            "X_t" to X_t,
            "X_total" to X_total,
            "I_p0" to I_p0,
            "I_base" to I_base,
            "X_c_base" to X_c_base,
            "X_t_base" to X_t_base
        )
    }

    fun calculateHPnEMShortCircuitCurrent(Uvn: Double, Rsn: Double, Xsn: Double, Rsmin: Double, Xsmin: Double): Map<String, Double> {
        val UvnTrans = 115.0
        val Sn = 6.3
        val UkMax = 11.1

        // Розрахунок реактансу трансформатора
        val Xt = round((UkMax * UvnTrans * UvnTrans) / (100 * Sn) * 100) / 100

        // Розрахунок опорів для нормального режиму
        val Xsh = Xsn + Xt
        val Zsh = round(sqrt(Rsn * Rsn + Xsh * Xsh) * 100) / 100

        // Розрахунок струму трифазного КЗ в нормальному режимі
        val Ish3Normal = round((Uvn * 1000) / (sqrt(3.0) * Zsh) * 100) / 100

        // Розрахунок струму двофазного КЗ в нормальному режимі
        val Ish2Normal = round((Ish3Normal * sqrt(3.0) / 2) * 100) / 100

        // Розрахунок опорів для мінімального режиму
        val XshMin = Xsmin + Xt
        val ZshMin = round(sqrt(Rsmin * Rsmin + XshMin * XshMin) * 100) / 100

        // Розрахунок струму трифазного КЗ в мінімальному режимі
        val Ish3Min = round((Uvn * 1000) / (sqrt(3.0) * ZshMin) * 100) / 100

        // Розрахунок струму двофазного КЗ в мінімальному режимі
        val Ish2Min = round((Ish3Min * sqrt(3.0) / 2) * 100) / 100

        // Коефіцієнт приведення
        val Unn = 11.0 // Напруга нижньої сторони, кВ
        val Kpr = (Unn * Unn) / (UvnTrans * UvnTrans)

        // Розрахунок опорів на шинах 10 кВ в нормальному режимі
        val RshN = Rsn * Kpr
        val XshN = Xsh * Kpr
        val ZshN = sqrt(RshN * RshN + XshN * XshN)

        // Розрахунок струмів на шинах 10 кВ в нормальному режимі
        val IshN3 = (Unn * 1000) / (sqrt(3.0) * ZshN)
        val IshN2 = IshN3 * sqrt(3.0) / 2

        // Розрахунок опорів на шинах 10 кВ в мінімальному режимі
        val RshNMin = Rsmin * Kpr
        val XshNMin = XshMin * Kpr
        val ZshNMin = sqrt(RshNMin * RshNMin + XshNMin * XshNMin)

        // Розрахунок струмів на шинах 10 кВ в мінімальному режимі
        val IshNMin3 = (Unn * 1000) / (sqrt(3.0) * ZshNMin)
        val IshNMin2 = IshNMin3 * sqrt(3.0) / 2

        // Відхідна лінія No 130
        val lineLength = 12.37
        val R0 = 0.64
        val X0 = 0.363

        // Розрахунок резистансу та реактансу лінії
        val Rl = lineLength * R0
        val Xl = lineLength * X0

        // Розрахунок повного опору в точці 10 в нормальному режимі
        val RetaN = Rl + RshN
        val XetaN = Xl + XshN
        val ZetaN = sqrt(RetaN * RetaN + XetaN * XetaN)

        // Розрахунок струмів в точці 10 в нормальному режимі
        val IlN3 = (Unn * 1000) / (sqrt(3.0) * ZetaN)
        val IlN2 = IlN3 * sqrt(3.0) / 2

        // Розрахунок повного опору в точці 10 в мінімальному режимі
        val RetaNMin = Rl + RshNMin
        val XetaNMin = Xl + XshNMin
        val ZetaNMin = sqrt(RetaNMin * RetaNMin + XetaNMin * XetaNMin)

        // Розрахунок струмів в точці 10 в мінімальному режимі
        val IlNMin3 = (Unn * 1000) / (sqrt(3.0) * ZetaNMin)
        val IlNMin2 = IlNMin3 * sqrt(3.0) / 2

        return mapOf(
            "Xt" to Xt,
            "Zsh" to Zsh,
            "Ish3Normal" to Ish3Normal,
            "Ish2Normal" to Ish2Normal,
            "Ish3Min" to Ish3Min,
            "Ish2Min" to Ish2Min,
            "Kpr" to Kpr,
            "ZshN" to ZshN,
            "IshN3" to IshN3,
            "IshN2" to IshN2,
            "ZshNMin" to ZshNMin,
            "IshNMin3" to IshNMin3,
            "IshNMin2" to IshNMin2,
            "Rl" to Rl,
            "Xl" to Xl,
            "ZetaN" to ZetaN,
            "IlN3" to IlN3,
            "IlN2" to IlN2,
            "ZetaNMin" to ZetaNMin,
            "IlNMin3" to IlNMin3,
            "IlNMin2" to IlNMin2
        )
    }
}

fun main() {
    val calculator = ShortCircuitCalculator()

    val cableSelectionResults = calculator.calculateCableSelection(Ik = 2500.0, tF = 2.5, Sm = 1300.0, Unom = 10.0, Tm = 4000.0)
    println("Cable Selection Results: $cableSelectionResults")

    val shortCircuitCurrentResults = calculator.calculateShortCircuitCurrent(S_k = 200.0, U_nom = 10.5)
    println("Short Circuit Current Results: $shortCircuitCurrentResults")

    val hPnEMShortCircuitCurrentResults = calculator.calculateHPnEMShortCircuitCurrent(Uvn = 115.0, Rsn = 10.65, Xsn = 24.02, Rsmin = 34.88, Xsmin = 65.68)
    println("HPnEM Short Circuit Current Results: $hPnEMShortCircuitCurrentResults")
}
