package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.trim()?.split(' ')?.filter { s -> s.length > 0 }
        val firstName: String? = parts?.getOrNull(0)
        val lastName: String? = parts?.getOrNull(1)
        return firstName to lastName
    }
}