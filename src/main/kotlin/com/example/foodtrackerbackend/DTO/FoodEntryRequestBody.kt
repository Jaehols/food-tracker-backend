package com.example.foodtrackerbackend.DTO

import java.sql.Timestamp
import java.util.*

class FoodEntryRequestBody(
        val entryTime: Timestamp,
        val mealDescription: String,
        val additionalComments: String,
        val kilojoules: Int,
)