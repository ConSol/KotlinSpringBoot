package de.consol.hackaburg.controller

import de.consol.hackaburg.dto.ApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@RestController
class JokeController {
    @Value("\${hackaburg.demo.backend}")
    lateinit var baseUrl: String

    @GetMapping("/joke")
    fun getRandomJoke(): String {
        val backend = RestTemplate()

        val apiResponse = backend.getForObject<ApiResponse>(baseUrl)

        return when(apiResponse?.type) {
            "success" -> apiResponse.value.joke
            else -> {
                "Germans don't laugh..."
            }
        }
    }
}
