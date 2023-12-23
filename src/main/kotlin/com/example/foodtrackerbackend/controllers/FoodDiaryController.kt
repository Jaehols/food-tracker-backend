package com.example.foodtrackerbackend.controllers

import com.example.foodtrackerbackend.DTO.FoodEntry
import com.example.foodtrackerbackend.DTO.FoodEntryRequestBody
import com.example.foodtrackerbackend.services.FoodEntryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*


@RestController
@RequestMapping("api/v1/food-diary")
class FoodDiaryController(private val foodEntryService: FoodEntryService) {

    @GetMapping("/entry/{entryId}")
    fun getFoodEntryById(@PathVariable entryId: UUID): ResponseEntity<FoodEntry>{
        //TODO actually implement this
        val foodEntry = foodEntryService.getFoodEntryById(entryId)
        return if (foodEntry != null){
            ResponseEntity.ok(foodEntry)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/new-entry")
    fun saveNewFoodEntry(@RequestBody foodEntryRequest: FoodEntryRequestBody): ResponseEntity<Void> {

        val newEntryId = foodEntryService.saveNewFoodEntry(
                UUID.randomUUID(),
                foodEntryRequest.userId,
                foodEntryRequest.entryTime,
                foodEntryRequest.mealDescription,
                foodEntryRequest.additionalComments,
                foodEntryRequest.kilojoules
        )

        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/entry/{newEntryId}")
            .buildAndExpand(newEntryId)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}