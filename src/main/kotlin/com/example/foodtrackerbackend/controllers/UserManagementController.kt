package com.example.foodtrackerbackend.controllers

import com.example.foodtrackerbackend.DTO.UserSettings
import com.example.foodtrackerbackend.DTO.UserSettingsRequestBody
import com.example.foodtrackerbackend.Enums.EnergyUnits
import com.example.foodtrackerbackend.Enums.Themes
import com.example.foodtrackerbackend.services.UserManagementService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


private const val baseAddress: String = "api/v1/user"
@RestController
@RequestMapping(baseAddress)
class UserManagementController(private val userManagementService: UserManagementService) {

    @GetMapping("/userSettings/{userId}")
    suspend fun getUserSettings(@PathVariable userId: String): ResponseEntity<UserSettings> {
        val userSettings = userManagementService.getUserSettings(userId)
        return if (userSettings != null) {
            ResponseEntity.ok(userSettings)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/userSettings/{userId}")
    suspend fun saveUserSettings(
        @PathVariable userId: String,
        @RequestBody userSettings: UserSettingsRequestBody,
        exchange: ServerWebExchange
    ): ResponseEntity<Void> {
        val newUserSettings = userManagementService.saveUserSettings(
            userId,
            userSettings.lastUpdated,
            Themes.valueOf(userSettings.preferredTheme),
            userSettings.energyShown,
            EnergyUnits.valueOf(userSettings.energyUnit)
        )

        val location: URI = createUri(newUserSettings.userId)

        return ResponseEntity.created(location).build()
    }

    private fun createUri(userId: String): URI {
        return UriComponentsBuilder.fromPath("$baseAddress/userSettings/{userId}")
            .buildAndExpand(userId)
            .toUri()
    }
}