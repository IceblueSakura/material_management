package com.example.material.dto

data class Pagination(
    val totalCount: Int = 1,
    val pageNumber: Int = 1,
    val pageSize: Int = 1,
    val totalPages: Int = 1,
)
