package com.example.material.service

import com.example.material.entity.MaterialSupplier
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SupplierService {
    suspend fun getAllSupplier(): Flow<MaterialSupplier>
    suspend fun getSupplierById(id: UUID): MaterialSupplier
}