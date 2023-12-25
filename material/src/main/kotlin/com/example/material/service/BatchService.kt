package com.example.material.service

import com.example.material.entity.MaterialBatch
import kotlinx.coroutines.flow.Flow
import java.util.*

interface BatchService {

    suspend fun insertBatch(batch: MaterialBatch): UUID

    suspend fun deleteBatchById(id:UUID): Boolean

    suspend fun updateBatch(batch: MaterialBatch): Boolean
    suspend fun getAllBatch(): Flow<MaterialBatch>
    suspend fun getBatchById(id: UUID): MaterialBatch

    suspend fun getBatchByMaterialId(id: UUID): Flow<MaterialBatch>

    /**
     * Retrieves a Flow of MaterialBatch objects that match a fuzzy search of the specified name.
     *
     * @param name The name to search for, using a fuzzy matching algorithm.e.g "100" can match "100g" and "100 kg."
     * @return A Flow of MaterialBatch objects that match the fuzzy search.
     */
    suspend fun getBatchBySpecificationFuzzy(name: String): Flow<MaterialBatch>
}