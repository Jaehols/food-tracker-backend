package com.example.foodtrackerbackend.services

import com.example.foodtrackerbackend.DTO.FoodEntry
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class FoodEntryService {
    fun SaveNewFoodEntry(entryId: UUID, userId: UUID, entryTime: Timestamp, mealDescription: String, additionalComments: String,kilojoules: Int): UUID {
        //TODO save down to database
        val foodEntry = FoodEntry(entryId, userId, entryTime, mealDescription, additionalComments, kilojoules);
        println(foodEntry)
        return foodEntry.entryId
    }
}