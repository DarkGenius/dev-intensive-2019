package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.sign

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits{
    SECOND {
        override fun plural(value: Long): String {
            val base = "секунд"
            val endings: List<String> = listOf("у", "ы", "")
            return getPluralInternal(value, base, endings)
        }

        override fun plural(value: Int): String = plural(value.toLong())
    },
    MINUTE {
        override fun plural(value: Long): String {
            val base = "минут"
            val endings: List<String> = listOf("у", "ы", "")
            return getPluralInternal(value, base, endings)
        }

        override fun plural(value: Int): String = plural(value.toLong())
    },
    HOUR {
        override fun plural(value: Long): String {
            val base = "час"
            val endings: List<String> = listOf("", "а", "ов")
            return getPluralInternal(value, base, endings)
        }

        override fun plural(value: Int): String = plural(value.toLong())
    },
    DAY {
        override fun plural(value: Long): String {
            val base = "д"
            val endings: List<String> = listOf("ень", "ня", "ней")
            return getPluralInternal(value, base, endings)
        }

        override fun plural(value: Int): String = plural(value.toLong())
    };

    protected fun getPluralInternal(value: Int, base: String, endings: List<String>): String {
        val vMod100 = value % 100
        val vMod10 = value % 10
        val endingIndex = when{
            vMod10 == 1 && vMod100 != 11 -> 0
            vMod10 in 2..4 && vMod100 !in 12..14 -> 1
            else -> 2
        }
        return "$value $base${endings[endingIndex]}"
    }

    protected fun getPluralInternal(value: Long, base: String, endings: List<String>): String {
        val vMod100 = value % 100
        val vMod10 = value % 10
        val endingIndex = when{
            vMod10 == 1L && vMod100 != 11L -> 0
            vMod10 in 2..4 && vMod100 !in 12..14 -> 1
            else -> 2
        }
        return "$value $base${endings[endingIndex]}"
    }

    abstract fun plural(value: Int): String
    abstract fun plural(value: Long): String
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits): Date {
    var time = this.time
    time += when(unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    val result = Date(time)
    return result
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val delta = date.time - this.time
    val deltaSec = delta / 1000
    val sign = sign(deltaSec.toDouble()).toInt()
    return when(abs(deltaSec)) {
        in 0..1 -> "только что"
        in 2..45 -> if (sign > 0) "несколько секунд назад" else "через несколько секунд"
        in 46..75 -> if (sign > 0) "минуту назад" else "через минуту"
        in 76..2700 -> if (sign > 0) "${TimeUnits.MINUTE.plural(deltaSec / 60)} назад" else "через ${TimeUnits.MINUTE.plural(-deltaSec / 60)}"
        in 2701..4500 -> "час назад"
        in 4501..79200 -> if (sign > 0) "${TimeUnits.HOUR.plural(deltaSec / 3600)} назад" else "через ${TimeUnits.HOUR.plural(-deltaSec / 3600)}"
        in 79201..93600 -> "день назад"
        in 93601..31104000 -> if (sign > 0) "${TimeUnits.DAY.plural(deltaSec / 86400)} назад" else "через ${TimeUnits.DAY.plural(-deltaSec / 86400)}"
        else -> if (sign > 0) "более года назад" else "более чем через год"
    }
}
