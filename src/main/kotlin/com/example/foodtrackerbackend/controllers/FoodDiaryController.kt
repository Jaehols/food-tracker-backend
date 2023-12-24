package com.example.foodtrackerbackend.controllers

import com.example.foodtrackerbackend.DTO.FoodEntry
import com.example.foodtrackerbackend.DTO.FoodEntryRequestBody
import com.example.foodtrackerbackend.services.FoodEntryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.*

private const val baseAddress: String = "api/v1/food-diary"
@RestController
@RequestMapping(baseAddress)
class FoodDiaryController(private val foodEntryService: FoodEntryService) {

    @GetMapping("/entry/{entryId}")
    suspend fun getFoodEntryById(@PathVariable entryId: UUID, @RequestParam userId: String): ResponseEntity<FoodEntry>{
        val foodEntry = foodEntryService.getFoodEntryById(entryId, userId)
        return if (foodEntry != null){
            ResponseEntity.ok(foodEntry)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/new-entry")
    suspend fun saveNewFoodEntry(
        @RequestBody foodEntryRequest: FoodEntryRequestBody,
        exchange: ServerWebExchange
    ): ResponseEntity<Void> {
        val newEntryId = foodEntryService.saveNewFoodEntry(
            UUID.randomUUID(),
            foodEntryRequest.userId,
            foodEntryRequest.entryTime,
            foodEntryRequest.mealDescription,
            foodEntryRequest.additionalComments,
            foodEntryRequest.kilojoules
        )

        val location: URI = createUri(newEntryId)

        return ResponseEntity.created(location).build()
    }

    private fun createUri(newEntryId: UUID): URI {
        return UriComponentsBuilder.fromPath("$baseAddress/entry/{newEntryId}")
            .buildAndExpand(newEntryId)
            .toUri()
    }
}