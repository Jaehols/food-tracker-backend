package com.example.foodtrackerbackend.services

import com.example.foodtrackerbackend.DTO.FoodEntry
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class FoodEntryService {

    fun getFoodEntryById(entryId: UUID): FoodEntry? {
        //TODO implement proper function
        val foodEntry = FoodEntry(entryId, "", Timestamp(System.currentTimeMillis()), "Handful of nuts", "It was yummy", 200)
        return foodEntry
    }
    fun saveNewFoodEntry(entryId: UUID, userId: String, entryTime: Timestamp, mealDescription: String, additionalComments: String, kilojoules: Int): UUID {
        //TODO save down to database
        val foodEntry = FoodEntry(entryId, userId, entryTime, mealDescription, additionalComments, kilojoules)
        println(foodEntry)
        return foodEntry.entryId
    }
}