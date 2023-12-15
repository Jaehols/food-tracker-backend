package com.example.foodtrackerbackend.DTO

import java.sql.Timestamp
import java.util.*

class FoodEntry(
    val entryId: UUID,
    val userId: UUID,
    val entryTime: Timestamp,
    val mealDescription: String,
    val additionalComments: String,
    val kilojoules: Int,
    ) {
}