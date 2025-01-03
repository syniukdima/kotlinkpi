package com.example.labs_mobile.labs.lab2.task1.utils

object EmissionCalculator {

    private fun calculateEmissionFactor(
        qr: Double,
        avin: Double,
        ar: Double,
        gvin: Double,
        nuz: Double
    ): Double {
        return (1_000_000 / qr) * avin * ar / (100 - gvin) * (1 - nuz)
    }

    fun calculateCoalEmissions(
        qr: Double, // Нижча теплота згоряння робочої маси вугілля
        avin: Double, // Масова частка горючих речовин у викиді
        ar: Double, // Вміст золи у вугіллі
        gvin: Double, // Масовий вміст горючих речовин у леткій золі
        nuz: Double, // Ефективність золоуловлювальної установки
        coalMass: Double // Маса вугілля
    ): Double {
        val ktv = calculateEmissionFactor(qr, avin, ar, gvin, nuz)
        return 1e-6 * ktv * qr * coalMass
    }

    fun calculateFuelOilEmissions(
        qr: Double, // Нижча теплота згоряння мазуту
        avin: Double, // Масова частка горючих речовин у викиді
        ar: Double, // Зольність мазуту
        gvin: Double, // Масовий вміст горючих речовин
        nuz: Double, // Ефективність золоуловлювальної установки
        fuelOilMass: Double // Маса мазуту
    ): Double {
        val ktv = calculateEmissionFactor(qr, avin, ar, gvin, nuz)
        return 1e-6 * ktv * qr * fuelOilMass
    }

    // Природний газ не утворює твердих частинок, тому викиди = 0
    fun calculateNaturalGasEmissions(): Double {
        return 0.0
    }
}

fun main() {
    // Дані для розрахунку викидів при спалюванні вугілля
    val coalQr = 20.47
    val coalAvin = 0.8
    val coalAr = 25.20
    val coalGvin = 1.5
    val coalNuz = 0.985
    val coalMass = 1_096_363.0

    val coalEmissions = EmissionCalculator.calculateCoalEmissions(
        coalQr, coalAvin, coalAr, coalGvin, coalNuz, coalMass
    )
    println("Валові викиди при спалюванні вугілля: $coalEmissions т")

    // Дані для розрахунку викидів при спалюванні мазуту
    val fuelOilQr = 39.48
    val fuelOilAvin = 1.0
    val fuelOilAr = 0.15
    val fuelOilGvin = 0.0
    val fuelOilNuz = 0.985
    val fuelOilMass = 70_945.0

    val fuelOilEmissions = EmissionCalculator.calculateFuelOilEmissions(
        fuelOilQr, fuelOilAvin, fuelOilAr, fuelOilGvin, fuelOilNuz, fuelOilMass
    )
    println("Валові викиди при спалюванні мазуту: $fuelOilEmissions т")

    // Валові викиди при спалюванні природного газу
    val naturalGasEmissions = EmissionCalculator.calculateNaturalGasEmissions()
    println("Валові викиди при спалюванні природного газу: $naturalGasEmissions т")

    // -------------------------------------------------------------------------
    // Дані для розрахунку викидів для твого варіанту:

    // Донецьке газове вугілля марки ГР
//    val doneckCoalQr = 20.0
//    val doneckCoalAvin = 0.8
//    val doneckCoalAr = 30.0
//    val doneckCoalGvin = 2.0
//    val doneckCoalNuz = 0.97
    val doneckCoalMass = 595_061.91

    val doneckCoalEmissions = EmissionCalculator.calculateCoalEmissions(
        coalQr, coalAvin, coalAr, coalGvin, coalNuz, doneckCoalMass
    )
    println("Валові викиди при спалюванні Донецького газового вугілля: $doneckCoalEmissions т")

    // Високосірчистий мазут марки 40
//    val fuelOil40Qr = 41.5
//    val fuelOil40Avin = 1.1
//    val fuelOil40Ar = 0.2
//    val fuelOil40Gvin = 0.0
//    val fuelOil40Nuz = 0.98
    val fuelOil40Mass = 125_029.33

    val fuelOil40Emissions = EmissionCalculator.calculateFuelOilEmissions(
        fuelOilQr, fuelOilAvin, fuelOilAr, fuelOilGvin, fuelOilNuz, fuelOil40Mass
    )
    println("Валові викиди при спалюванні високосірчистого мазуту марки 40: $fuelOil40Emissions т")

    // Природний газ із газопроводу Уренгой-Ужгород
    val naturalGasMass = 142_828.90
    val naturalGasEmissions2 = EmissionCalculator.calculateNaturalGasEmissions()
    println("Валові викиди при спалюванні природного газу (Уренгой-Ужгород): $naturalGasEmissions2 т")
}

//Реалізуй це на котліні , у вигляді як я надам нижче
//і дай відповіді на питання
//
//Завдання 1
//Написати мобільний калькулятор для розрахунку валових викидів шкідливих речовин у вигляді суспендованих твердих частинок при спалювання вугілля, мазуту та природного газу якщо розглядається:
//Енергоблок з котлом, призначеним для факельного спалювання вугілля з високим вмістом летких, типу газового або довгополуменевого, з рідким шлаковидаленням. Номінальна паропродуктивність котла енергоблока становить 950 т/год, а середня фактична паропродуктивність – 760 т/год. На ньому застосовується ступенева подача повітря та рециркуляція димових газів. Пароперегрівачі котла очищуються при зупинці блока. Для уловлювання твердих частинок використовується електростатичний фільтр типу ЕГА з ефективністю золовловлення 0,985.
//Установки для очищення димових газів від оксидів азоту та сірки відсутні.
//За звітний період використовувалось таке паливо:
//- донецьке газове вугілля марки ГР – 1.096.363 т;
//- високосірчистий мазут марки 40 – 70.945 т;
//- природний газ із газопроводу Уренгой-Ужгород – 84 762 тис. м3.
//
//За даними елементного та технічного аналізу склад робочої маси вугілля наступний, %: - вуглець (Cr) – 52,49;
//- водень (Hr) – 3,50;
//- кисень (Or) – 4,99;
//- азот (Nr) – 0,97;
//- сірка (Sr) – 2,85;
//- зола (Ar) – 25,20;
//- волога (Wr) – 10,00;
//- леткі речовини (Vr) – 25,92.
//Нижча теплота згоряння робочої маси вугілля становить 20,47 МДж/кг. Технічний аналіз уловленої золи та шлаку показав, що масовий вміст горючих речовин у леткій золі Гвин дорівнює 1,5 %, а в шлаці Гшл – 0,5 %.
//За даними таблиці А.3 (додаток А) склад горючої маси мазуту настуgний, %:
//- вуглець – 85,50;
//- водень – 11,20;
//- кисень та азот – 0,80;
//- сірка – 2,50;
//- нижча теплота згоряння горючої маси мазуту дорівнює 40,40 МДж/кг;
//- вологість робочої маси палива – 2,00 %;
//- зольність сухої маси – 0,15 %;
//- вміст ванадію (V) – 333,3 мг/кг (= 22220,15).
//За даними таблиці А.3 (додаток А) об’ємний склад сухої маси природного газу
//становить, %:
//- метан (CH4) – 98,90;
//- етан (C2H6) – 0,12;
//- пропан (C3H8) – 0,011;
//- бутан (C4H10) – 0,01;
//- вуглекислий газ (CO2) – 0,06;
//- азот (N2) – 0,90;
//- об’ємна нижча теплота згоряння газу дорівнює 33,08 МДж/м3
//- густина – 0,723 кг/м3 при нормальних умовах.
//2.2.1. Контрольний приклад
//Порядок розрахунку розглянемо на контрольному прикладі.
//1. Розрахуємо валовий викид твердих частинок при спалюванні вугілля.
//Показник емісії твердих частинок визначається як специфічний і розраховується за
//формулою (2.2), а саме:
//k(тв) = 10^6/Q^r(i)*a(вин)*А^r/(100-Г(вин))*(1-ню(зу))+k(тв)s г/ГДж.
//
//Сіркоочисна установка відсутня, тому викиду твердих частинок сорбенту та продуктів взаємодії сорбенту та оксидів сірки немає. Ефективність золоуловлювальної установки ηзу за даними останніх випробувань становить 0,985. Тоді показник емісії твердих частинок при
//спалюванні вугілля становитиме:
//k(тв) = 10^6/20.47*0.8*25.20/(100-1.5)*(1-0.985)= 150 г/ГДж.
//
//А валовий викид (див. формулу (2.1)) при спалюванні вугілля становитиме:
//Е(тв) = 10^(-6)*k(тв)*Q^r(i)*B = 10^(-6) * 150 * 20.47 * 1096363 = 3366 т
//
//2. Розрахуємо валовий викид твердих частинок при спалюванні мазуту.
//Показник емісії твердих частинок визначається як специфічний і розраховується за
//формулою (2.2), а саме:
//k(тв) = 10^6/Q^r(i)*a(вин)*А^r/(100-Г(вин))*(1-ню(зу))+k(тв)s г/ГДж.
//
//Сіркоочисна установка відсутня, тому викиду твердих частинок сорбенту та продуктів взаємодії сорбенту та оксидів сірки немає. Масовий вміст горючих речовин у викиді твердих частинок Гвин становить 0 %. Ефективність золоуловлювальної установки ηзу, за даними останніх випробувань, становить 0,985. Тоді показник емісії твердих частинок при спалюванні
//мазуту становитиме:
//k(тв) = 10^6/30.48*1*0.15/(100-0)*(1-0.985)= 0.57 г/ГДж.
//
//А валовий викид (див. формулу (2.1)) при спалюванні мазуту становитиме:
//Е(тв) = 10^(-6)*k(тв)*Q^r(i)*B = 10^(-6) * 0.57 * 39.48 * 70945 = 1.60 т
//
//3. Розрахуємо валовий викид твердих частинок при спалюванні мазуту.
//При спалюванні природного газу тверді частинки відсутні. Тоді:
//- показник емісії твердих частинок при спалюванні мазуту становитиме:
//Поміркуйте і дайте відповідь!
//- валовий викид (див. формулу (2,1)) при спалюванні мазуту становитиме:
//Поміркуйте і дайте відповідь!
//
//2.2.2. Результат контрольно прикладу
//1. Для заданого енергоблоку і відповідним умовам роботи:
//1.1. Показник емісії твердих частинок при спалюванні вугілля становитиме: 150 г/ГДж;
//1.2. Валовий викид при спалюванні вугілля становитиме: 3366 т.;
//1.3. Показник емісії твердих частинок при спалюванні мазуту становитиме: 0,57 г/ГДж;
//1.4. Валовий викид при спалюванні мазуту становитиме: 1,60 т.;
//1.5. Показник емісії твердих частинок при спалюванні природного газу становитиме: ???
//г/ГДж;
//1.6. Валовий викид при спалюванні природного газу становитиме: ??? т.