package com.example.foodtrackerbackend.DTO
import java.util.*
class UserSettingsRequestBody (
    val lastUpdated: Date,
    val preferredTheme: String,
    val energyShown: Boolean,
    val energyUnit: String,
    )