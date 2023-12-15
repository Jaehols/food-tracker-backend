package com.example.foodtrackerbackend.controllers

import com.example.foodtrackerbackend.DTO.FoodEntry
import com.example.foodtrackerbackend.services.FoodEntryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.sql.Timestamp
import java.util.*


@RestController
@RequestMapping("api/v1/food-diary")
class FoodDiaryController(private val foodEntryService: FoodEntryService) {

    @GetMapping("/entry/{entryId}")
    fun getFoodEntryById(@PathVariable entryId: UUID){
        //TODO actually implement this
    }

    @PostMapping("/new-entry")
    fun saveNewFoodEntry(@RequestParam entryTime: Timestamp, @RequestParam mealDescription: String, @RequestParam additionalComments: String, @RequestParam kilojoules: Int): ResponseEntity<Void>{
        val newEntryId = foodEntryService.SaveNewFoodEntry(UUID.randomUUID(), UUID.randomUUID(), entryTime, mealDescription, additionalComments, kilojoules)

        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/entry/{newEntryId}")
            .buildAndExpand(newEntryId)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}