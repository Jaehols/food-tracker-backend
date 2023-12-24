package com.example.foodtrackerbackend.services

import com.example.foodtrackerbackend.DTO.FoodEntry
import com.example.foodtrackerbackend.Utilities.MongoUtilities.Companion.getOrCreateCollection
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.InsertOneOptions
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.firstOrNull
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FoodEntryService(@Autowired private val mongoClient: MongoClient) {

    val database = mongoClient.getDatabase("food-diary")
    suspend fun getFoodEntryById(entryId: UUID, userId: String): FoodEntry? {
        val collection = getOrCreateCollection(database, userId)
        val document = collection.find(eq("entryId", entryId.toString())).firstOrNull()

        return document?.let { doc ->
            FoodEntry(
                entryId = UUID.fromString(doc.getString("entryId")),
                userId = doc.getString("userId"),
                entryTime = doc.getDate("entryTime"),
                createdTime = doc.getDate("createdTime"),
                mealDescription = doc.getString("mealDescription"),
                additionalComments = doc.getString("additionalComments"),
                kilojoules = doc.getInteger("kilojoules")
            )
        }
    }
    suspend fun saveNewFoodEntry(entryId: UUID, userId: String, entryTime: Date, mealDescription: String, additionalComments: String, kilojoules: Int): UUID {

        val foodEntry = FoodEntry(entryId, userId, entryTime, Date(), mealDescription, additionalComments, kilojoules)
        val collection = getOrCreateCollection(database, userId)
        val document = Document()
            .append("entryId", foodEntry.entryId.toString())
            .append("userId", foodEntry.userId)
            .append("entryTime", foodEntry.entryTime)
            .append("createdTime", foodEntry.createdTime)
            .append("mealDescription", foodEntry.mealDescription)
            .append("additionalComments", foodEntry.additionalComments)
            .append("kilojoules", foodEntry.kilojoules)
            .append("entryType", foodEntry.entryType)

        collection.insertOne(document, InsertOneOptions().bypassDocumentValidation(false))
        return foodEntry.entryId
    }
}