package com.example.material.dto

data class MaterialBatchDTO(
    val id: String? = "",
    val materialId: String,
    val supplierId: String,
    val specification: String,
    val description: String,
)
