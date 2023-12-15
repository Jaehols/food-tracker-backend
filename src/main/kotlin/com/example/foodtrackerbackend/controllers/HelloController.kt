package com.example.foodtrackerbackend.controllers

import com.example.foodtrackerbackend.services.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping("api/v1")
class HelloController(private val helloService: HelloService) {

    @GetMapping("/hello")
    fun sayHello(@RequestParam name: String): String {
        return helloService.CreateHelloMessage(name)
    }
}