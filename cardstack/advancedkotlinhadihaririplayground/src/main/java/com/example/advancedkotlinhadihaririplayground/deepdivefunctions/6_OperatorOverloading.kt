package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

import java.lang.StringBuilder

class Time(val hours: Int, val mins: Int) {
    operator fun plus(time: Time): Time {
        val minutes = this.mins + time.mins
        val hoursInMinutes = minutes/ 60
        val remainingMinutes = minutes % 60
        val hours = this.hours + hoursInMinutes
        return Time(
            hours,
            remainingMinutes
        )
    }
}

operator fun StringBuilder.plus(stringBuilder: StringBuilder) {
    stringBuilder.forEach { this.append(it) }
}

fun main(){

    val newTimePlus = Time(
        10,
        40
    ).plus(
        Time(
            3,
            20
        )
    )

    // Use operator keyword here
    val newTime = Time(
        10,
        40
    ) + Time(
        3,
        20
    )

    val sb = StringBuilder()
    for(str in sb) {
        str + "Value"
    }
}