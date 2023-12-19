package com.example.material.service.impl

import com.example.material.entity.MaterialSupplier
import com.example.material.repository.MaterialSupplierRepository
import com.example.material.service.SupplierService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class SupplierServiceImpl(
    @Autowired private val supplierRepository: MaterialSupplierRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : SupplierService {
    override suspend fun getAllSupplier(): Flow<MaterialSupplier> {
        TODO("Not yet implemented")
    }

    override suspend fun getSupplierById(id: UUID): MaterialSupplier {
        TODO("Not yet implemented")
    }

}