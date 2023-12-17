package com.example.material.controller

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

@SpringBootTest
@AutoConfigureWebTestClient
class MaterialControllerTest(@Autowired private val webClient: WebTestClient) {
    @Test
    fun getAll() {
        webClient.get()
            .uri("/material")
            .accept(MediaType.APPLICATION_PROTOBUF)
            .exchange()
            .expectStatus().isOk
    }
}