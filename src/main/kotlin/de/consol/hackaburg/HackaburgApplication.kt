package de.consol.hackaburg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HackaburgApplication

fun main(args: Array<String>) {
    runApplication<HackaburgApplication>(*args)
}
