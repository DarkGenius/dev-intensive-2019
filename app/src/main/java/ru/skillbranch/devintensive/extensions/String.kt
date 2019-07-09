package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val filler = "..."

    if (this.length <= count) {
        return this
    }

    val newStr = this.substring(0, count)
    val trimmedNewStr = newStr.trimEnd()
    if (newStr.length - trimmedNewStr.length <= 1)
        return trimmedNewStr + filler
    else
        return trimmedNewStr
}

fun String.stripHtml(): String {
    var newString = this
    newString = Regex("<[^>]*>").replace(newString, "")
    newString = Regex("&[A-za-z]*;").replace(newString, "")
    newString = Regex("(?<=\\b) {2,}(?=\\b)").replace(newString, " ")

    return newString
}