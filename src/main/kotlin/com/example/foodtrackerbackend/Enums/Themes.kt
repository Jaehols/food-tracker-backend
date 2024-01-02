package com.example.foodtrackerbackend.Enums

enum class Themes {
    `CLASSIC-GREEN`, LIGHT, `PURPLE-MOONLIGHT`, PINK;

    companion object {
        fun fromString(string: String): Themes {
            return when (string) {
                "CLASSIC-GREEN" -> `CLASSIC-GREEN`
                "LIGHT" -> LIGHT
                "PURPLE-MOONLIGHT" -> `PURPLE-MOONLIGHT`
                "PINK" -> PINK
                else -> throw IllegalArgumentException("Invalid theme: $string")
            }
        }
    }
}