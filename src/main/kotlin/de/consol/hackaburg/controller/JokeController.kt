package de.consol.hackaburg.controller

import de.consol.hackaburg.dto.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@RestController
class JokeController {
    @Value("\${hackaburg.demo.backend}")
    lateinit var baseUrl: String

    private val log = LoggerFactory.getLogger(this.javaClass)

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

    @ExceptionHandler(value = [ResourceAccessException::class])
    fun handleNetworkException(rae: ResourceAccessException): ResponseEntity<String> {
        log.error("Unable to access backend: ${rae.message}")
        return ResponseEntity("Unable to access backend: $baseUrl", HttpStatus.BAD_GATEWAY)
    }
}
