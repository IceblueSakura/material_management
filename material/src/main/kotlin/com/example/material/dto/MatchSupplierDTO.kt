package com.example.material.dto

data class MatchSupplierDTO(
    val id: String? = "",
    val supplierName: String,  // unique
    val description: String,
    val contactInfo: String,
)