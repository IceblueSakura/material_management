package com.example.material.dto

import com.example.material.entity.Material

data class MaterialDetails(
    val material: Material,
    val batch: MaterialBatchSupplier
)

