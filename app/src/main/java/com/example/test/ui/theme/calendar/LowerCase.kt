package com.example.test.ui.theme.calendar
fun String.lowercaseFirstChar(): String {
    if (this.isEmpty()) return this
    return this[0].uppercaseChar() + this.substring(1).lowercase()
}
