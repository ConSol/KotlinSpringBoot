package de.consol.hackaburg.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Joke(val id: Int, val joke: String, val categories: Array<String>)
