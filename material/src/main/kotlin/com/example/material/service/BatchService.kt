package com.example.material.service

import com.example.material.entity.MaterialBatch
import kotlinx.coroutines.flow.Flow
import java.util.*

interface BatchService {
    suspend fun getAllBatch(): Flow<MaterialBatch>
    suspend fun getBatchById(id: UUID): MaterialBatch


}