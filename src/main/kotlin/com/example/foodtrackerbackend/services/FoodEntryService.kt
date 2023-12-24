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

    companion object {
        private const val DATABASE_NAME = "food-diary"
        private const val ENTRY_ID_FIELD = "entryId"
        private const val USER_ID_FIELD = "userId"
        private const val ENTRY_TIME_FIELD = "entryTime"
        private const val CREATED_TIME_FIELD = "createdTime"
        private const val MEAL_DESCRIPTION_FIELD = "mealDescription"
        private const val ADDITIONAL_COMMENTS_FIELD = "additionalComments"
        private const val KILOJOULES_FIELD = "kilojoules"
        private const val ENTRY_TYPE_FIELD = "entryType"
    }

    val database = mongoClient.getDatabase(DATABASE_NAME)
    suspend fun getFoodEntryById(entryId: UUID, userId: String): FoodEntry? {
        val collection = getOrCreateCollection(database, userId)
        val document = collection.find(eq(ENTRY_ID_FIELD, entryId.toString())).firstOrNull()

        return document?.let { doc ->
            FoodEntry(
                entryId = UUID.fromString(doc.getString(ENTRY_ID_FIELD)),
                userId = doc.getString(USER_ID_FIELD),
                entryTime = doc.getDate(ENTRY_TIME_FIELD),
                createdTime = doc.getDate(CREATED_TIME_FIELD),
                mealDescription = doc.getString(MEAL_DESCRIPTION_FIELD),
                additionalComments = doc.getString(ADDITIONAL_COMMENTS_FIELD),
                kilojoules = doc.getInteger(KILOJOULES_FIELD)
            )
        }
    }
    suspend fun saveNewFoodEntry(entryId: UUID, userId: String, entryTime: Date, mealDescription: String, additionalComments: String, kilojoules: Int): UUID {

        val foodEntry = FoodEntry(entryId, userId, entryTime, Date(), mealDescription, additionalComments, kilojoules)
        val collection = getOrCreateCollection(database, userId)
        val document = Document()
            .append(ENTRY_ID_FIELD, foodEntry.entryId.toString())
            .append(USER_ID_FIELD, foodEntry.userId)
            .append(ENTRY_TIME_FIELD, foodEntry.entryTime)
            .append(CREATED_TIME_FIELD, foodEntry.createdTime)
            .append(MEAL_DESCRIPTION_FIELD, foodEntry.mealDescription)
            .append(ADDITIONAL_COMMENTS_FIELD, foodEntry.additionalComments)
            .append(KILOJOULES_FIELD, foodEntry.kilojoules)
            .append(ENTRY_TYPE_FIELD, foodEntry.entryType)

        collection.insertOne(document, InsertOneOptions().bypassDocumentValidation(false))
        return foodEntry.entryId
    }
}