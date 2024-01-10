package com.example.foodtrackerbackend.Utilities

import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import org.bson.Document

class MongoUtilities {
    companion object {
        suspend fun getOrCreateCollection(
            database: MongoDatabase,
            collectionName: String
        ): MongoCollection<Document> = coroutineScope {
            val collectionNames = database.listCollectionNames().toList()
            if (collectionName !in collectionNames) {
                database.createCollection(collectionName)
            }
            database.getCollection(collectionName)
        }
    }
}