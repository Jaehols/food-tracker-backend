package com.example.foodtrackerbackend.DTO
import com.example.foodtrackerbackend.Enums.EnergyUnits
import com.example.foodtrackerbackend.Enums.Themes
import java.util.*

class UserSettings(
    val userId: String,
    val lastUpdated: Date,
    val preferredTheme: Themes,
    val energyShown: Boolean,
    val energyUnit: EnergyUnits,
    val entryType: String = "UserSettings"
    )