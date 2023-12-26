package com.example.material.repository

import com.example.material.entity.MaterialSupplier
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface MaterialSupplierRepository : CoroutineCrudRepository<MaterialSupplier, UUID> {
    suspend fun findByIdAndDeleted(id: UUID, deleted: Boolean): MaterialSupplier?

    suspend fun findAllByDeleted(deleted: Boolean): Flow<MaterialSupplier>

    suspend fun findBySupplierNameAndDeleted(
        supplierName: String,
        deleted: Boolean
    ): MaterialSupplier?  // supplierName is unique column

}