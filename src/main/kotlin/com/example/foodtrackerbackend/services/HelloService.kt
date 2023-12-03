package com.example.foodtrackerbackend.services
import org.springframework.stereotype.Service

@Service
class HelloService {
    fun CreateHelloMessage(name: String): String{
        return "Hello $name"
    }
}