package com.example.foodtrackerbackend.Enums

enum class EnergyUnits {
    KILOJOULES, CALORIES;

    companion object {
        fun fromString(string: String): EnergyUnits {
            return when (string) {
                "KILOJOULES" -> KILOJOULES
                "CALORIES" -> CALORIES
                else -> throw IllegalArgumentException("Invalid energy unit: $string")
            }
        }
    }
}