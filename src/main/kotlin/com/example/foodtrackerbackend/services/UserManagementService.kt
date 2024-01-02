package com.example.foodtrackerbackend.services

import com.example.foodtrackerbackend.DTO.UserSettings
import com.example.foodtrackerbackend.Enums.EnergyUnits
import com.example.foodtrackerbackend.Enums.Themes
import com.example.foodtrackerbackend.Utilities.MongoUtilities
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.firstOrNull
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserManagementService(@Autowired private val mongoClient: MongoClient) {

    companion object{
        private const val DATABASE_NAME = "food-diary"
        private const val ENTRY_TYPE_FIELD = "entryType"
        private const val USER_SETTINGS_ENTRY_TYPE = "UserSettings"
    }

    val database = mongoClient.getDatabase(DATABASE_NAME)

    suspend fun getUserSettings(userId: String): UserSettings? {
        val collection = MongoUtilities.getOrCreateCollection(database, userId)
        val document = collection.find(eq(ENTRY_TYPE_FIELD, USER_SETTINGS_ENTRY_TYPE)).firstOrNull()

        return document?.let { doc ->
            UserSettings(
                userId = doc.getString("userId"),
                lastUpdated = doc.getDate("lastUpdated"),
                preferredTheme = Themes.fromString(doc.getString("preferredTheme")),
                energyShown = doc.getBoolean("energyShown"),
                energyUnit = EnergyUnits.fromString(doc.getString("energyUnit"))
            )
        }
    }

    suspend fun saveUserSettings(userId: String, lastUpdated: Date, preferredTheme: Themes, energyShown: Boolean, energyUnit: EnergyUnits): UserSettings {
        val userSettings = UserSettings(userId, lastUpdated, preferredTheme, energyShown, energyUnit)
        val collection = MongoUtilities.getOrCreateCollection(database, userId)
        collection.deleteMany(eq(ENTRY_TYPE_FIELD, USER_SETTINGS_ENTRY_TYPE))
        val document = Document()
            .append("userId", userSettings.userId)
            .append("lastUpdated", userSettings.lastUpdated)
            .append("preferredTheme", userSettings.preferredTheme)
            .append("energyShown", userSettings.energyShown)
            .append("energyUnit", userSettings.energyUnit)
            .append("entryType", USER_SETTINGS_ENTRY_TYPE)

        collection.insertOne(document)
        return userSettings
    }
}