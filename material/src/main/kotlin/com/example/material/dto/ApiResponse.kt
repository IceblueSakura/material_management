package com.example.material.dto

data class ApiResponse<out T>(
    val status: Boolean = true,
    val message: Set<String> = emptySet(),
    val error: Set<String> = emptySet(),
    val pagination: Pagination? = null,
    val data: T?
)