package com.example.foodtrackerbackend.DTO

import java.sql.Timestamp

class FoodEntryRequestBody(
        val entryTime: Timestamp,
        val userId: String,
        val mealDescription: String,
        val additionalComments: String,
        val kilojoules: Int,
)