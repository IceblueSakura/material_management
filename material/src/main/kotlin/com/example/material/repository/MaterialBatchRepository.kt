package com.example.material.repository

import com.example.material.entity.MaterialBatch
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MaterialBatchRepository : CoroutineCrudRepository<MaterialBatch, UUID> {
    suspend fun findByIdAndDeleted(id: UUID, deleted: Boolean): MaterialBatch?

    suspend fun findAllByDeleted(deleted: Boolean): Flow<MaterialBatch>

    suspend fun findByMaterialIdAndDeleted(materialId: UUID, deleted: Boolean): Flow<MaterialBatch>

    suspend fun findBySupplierIdAndDeleted(supplierId: UUID, deleted: Boolean): Flow<MaterialBatch>
}