package com.example.material.service.impl

import com.example.material.entity.MaterialBatch
import com.example.material.repository.MaterialSupplierRepository
import com.example.material.service.BatchService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class BatchServiceImpl(
    @Autowired private val materialSupplierRepository: MaterialSupplierRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : BatchService {
    override suspend fun getAllBatch(): Flow<MaterialBatch> {
        TODO("Not yet implemented")
    }

    override suspend fun getBatchById(id: UUID): MaterialBatch {
        TODO("Not yet implemented")
    }
}