package com.example.material.service.impl

import com.example.material.entity.MaterialBatch
import com.example.material.repository.MaterialBatchRepository
import com.example.material.service.BatchService
import com.example.material.utils.insertEntity
import com.example.material.utils.updateEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class BatchServiceImpl(
    @Autowired private val batchRepository: MaterialBatchRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : BatchService {
    override suspend fun insertBatch(batch: MaterialBatch): UUID {
        return insertEntity(batchRepository, batch.copy(id = null))
    }

    override suspend fun deleteBatchById(id: UUID): Boolean {
        val batch = batchRepository.findById(id)
        return if (batch != null) {
            updateEntity(batchRepository, batch.copy(deleted = true)) // update existBatch copy
        } else false
    }

    override suspend fun updateBatch(batch: MaterialBatch): Boolean {
        return updateEntity(batchRepository, batch)
    }


    override suspend fun getAllBatch(): Flow<MaterialBatch> {
        TODO("Not yet implemented")
    }

    override suspend fun getBatchById(id: UUID): MaterialBatch {
        TODO("Not yet implemented")
    }

    override suspend fun getBatchByMaterialId(id: UUID): Flow<MaterialBatch> {
        TODO("Not yet implemented")
    }

    override suspend fun getBatchBySpecificationFuzzy(name: String): Flow<MaterialBatch> {
        TODO("Not yet implemented")
    }
}