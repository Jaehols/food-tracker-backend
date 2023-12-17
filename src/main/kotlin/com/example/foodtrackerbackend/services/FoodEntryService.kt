package com.example.foodtrackerbackend.services

import com.example.foodtrackerbackend.DTO.FoodEntry
import org.springframework.stereotype.Service
import java.sql.Time
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class FoodEntryService {

    fun GetFoodEntryById(entryId: UUID): FoodEntry {
        //TODO implement proper function
        val foodEntry = FoodEntry(entryId, UUID.randomUUID(), Timestamp(System.currentTimeMillis()), "Handful of nuts", "It was yummy", 200)
        return foodEntry
    }
    fun SaveNewFoodEntry(entryId: UUID, userId: UUID, entryTime: Timestamp, mealDescription: String, additionalComments: String,kilojoules: Int): UUID {
        //TODO save down to database
        val foodEntry = FoodEntry(entryId, userId, entryTime, mealDescription, additionalComments, kilojoules);
        println(foodEntry)
        return foodEntry.entryId
    }
}