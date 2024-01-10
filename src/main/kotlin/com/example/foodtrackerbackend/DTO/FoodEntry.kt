package com.example.foodtrackerbackend.DTO

import java.util.*

class FoodEntry(
    val entryId: UUID,
    val userId: String,
    val entryTime: Date,
    val createdTime: Date,
    val mealDescription: String,
    val additionalComments: String,
    val kilojoules: Int,
    val entryType: String = "FoodEntry",
    )