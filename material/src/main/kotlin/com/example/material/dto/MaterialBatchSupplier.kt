package com.example.material.dto

import java.sql.Timestamp

data class MaterialBatchSupplier(
    val description: String,
    val createAt: Timestamp,
    val supplierName: String,
    val supplierId: String,
)
