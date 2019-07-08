package ru.skillbranch.devintensive.utils

object Utils {

    val transMap: Map<String, String> = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.trim()?.split(' ')?.filter { s -> s.length > 0 }
        val firstName: String? = parts?.getOrNull(0)
        val lastName: String? = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? =
            listOf<String?>(firstName?.trim(), lastName?.trim())
                .filter { s -> s != null && s.length > 0 }
                .map { s -> s?.get(0)?.toUpperCase() }
                .joinToString("").ifEmpty { null }

    fun transliteration(payload: String, divider: String = " "): String {
        val mapCharFunc: (Char) -> String = { c ->
            var t = transMap[c.toLowerCase().toString()]
            if (t != null) {
                if (c.isUpperCase()) t = t.capitalize()
            }
            else {
                t = c.toString()
            }
            t
        }

        val parts = payload.split(" ")
                .map { s -> s.map(mapCharFunc).joinToString("") }
        val result = parts.joinToString(divider)
        return result

    }
}