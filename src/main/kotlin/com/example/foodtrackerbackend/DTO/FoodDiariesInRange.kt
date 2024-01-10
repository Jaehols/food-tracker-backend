package com.example.foodtrackerbackend.DTO

import java.time.LocalDate

class FoodDiariesInRange(
    val startingDate: LocalDate,
    val endDate: LocalDate,
    val entries: List<FoodEntry>
) {
}