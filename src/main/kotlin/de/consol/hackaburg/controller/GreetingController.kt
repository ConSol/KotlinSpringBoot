package de.consol.hackaburg.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("greet")
    fun greet(@RequestParam name: String?): String {
        return "Hello ${name ?: "world"}!"
    }
}
