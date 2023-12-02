package com.example.foodtrackerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodTrackerBackendApplication

fun main(args: Array<String>) {
    runApplication<FoodTrackerBackendApplication>(*args)
}
