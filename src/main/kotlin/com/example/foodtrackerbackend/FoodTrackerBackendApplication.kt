package com.example.foodtrackerbackend

import MongoConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(MongoConfig::class)
class FoodTrackerBackendApplication

fun main(args: Array<String>) {
    runApplication<FoodTrackerBackendApplication>(*args)
}
