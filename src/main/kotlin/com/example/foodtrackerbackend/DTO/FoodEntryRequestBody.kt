package com.example.foodtrackerbackend.DTO

import java.util.*

class FoodEntryRequestBody(
        val entryTime: Date,
        val userId: String,
        val mealDescription: String,
        val additionalComments: String,
        val kilojoules: Int,
)