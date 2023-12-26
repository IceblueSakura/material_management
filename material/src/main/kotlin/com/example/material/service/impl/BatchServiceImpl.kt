package com.example.material.service.impl

import com.example.material.dto.MaterialBatchDTO
import com.example.material.entity.MaterialBatch
import com.example.material.exception.CustomException
import com.example.material.repository.MaterialBatchRepository
import com.example.material.service.BatchService
import com.example.material.utils.insertEntity
import com.example.material.utils.updateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BatchServiceImpl(
    @Autowired private val batchRepository: MaterialBatchRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : BatchService {
    @Transactional
    override suspend fun insertBatch(batch: MaterialBatch): UUID {
        return insertEntity(batchRepository, batch)
    }

    @Transactional
    override suspend fun deleteBatchById(id: UUID): Boolean {
        val batch = batchRepository.findById(id)
        return if (batch != null && !batch.deleted) {
            updateEntity(batchRepository, batch.copy(deleted = true)) // update existBatch copy
        } else false
    }

    @Transactional
    override suspend fun updateBatch(batch: MaterialBatch): Boolean {
        return updateEntity(batchRepository, batch)
    }


    override suspend fun getAllBatch(): Flow<MaterialBatch> {
        return batchRepository.findAllByDeleted(deleted = false)
    }

    override suspend fun getBatchById(id: UUID): MaterialBatch {
        val exist = batchRepository.findById(id)
        return exist ?: throw CustomException(404, "MaterialBatch not found")
    }

    override suspend fun getBatchByMaterialId(id: UUID): Flow<MaterialBatch> {
        return batchRepository.findByMaterialIdAndDeleted(id, deleted = false)
    }

    override suspend fun getBatchBySpecificationFuzzy(specification: String): Flow<MaterialBatch> {
        val query = Query.query(
            Criteria.where("specification").like("%$ specification%").and("deleted").`is`(false)
        )  // fuzzy matching
        return r2dbcEntityTemplate.select(query, MaterialBatch::class.java).asFlow()
    }

    @Transactional
    override suspend fun insertBatchDTO(batch: MaterialBatchDTO): UUID {
        return insertBatch(
            MaterialBatch(
                id = null, // auto generate
                materialId = UUID.fromString(batch.materialId),
                supplierId = UUID.fromString(batch.materialId),
                specification = batch.specification,
                description = batch.description,
                createAt = null,  // auto generate
                version = 0,
            )
        )
    }

    @Transactional
    override suspend fun updateBatchDTO(batch: MaterialBatchDTO): Boolean {
        val exist = batchRepository.findById(UUID.fromString(batch.id))
        return if (exist != null) {
            updateBatch(
                exist.copy(
                    materialId = UUID.fromString(batch.materialId),
                    supplierId = UUID.fromString(batch.materialId),
                    specification = batch.specification,
                    description = batch.description,
                )
            )
        } else false
    }


}