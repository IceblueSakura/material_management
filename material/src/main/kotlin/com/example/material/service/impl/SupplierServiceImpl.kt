package com.example.material.service.impl

import com.example.material.entity.MaterialSupplier
import com.example.material.repository.MaterialSupplierRepository
import com.example.material.service.SupplierService
import com.example.material.utils.insertEntity
import com.example.material.utils.updateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import java.util.*

@Service
class SupplierServiceImpl(
    @Autowired private val supplierRepository: MaterialSupplierRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : SupplierService {
    override suspend fun insertSupplier(supplier: MaterialSupplier): UUID {
        return insertEntity(supplierRepository, supplier.copy(id = null))
    }

    override suspend fun deleteSupplierById(id: UUID): Boolean {
        val supplier = supplierRepository.findById(id)
        return if (supplier != null) {
            updateEntity(supplierRepository, supplier.copy(deleted = true)) // update existBatch copy
        } else false
    }

    override suspend fun updateSupplier(supplier: MaterialSupplier): Boolean {
        return updateEntity(supplierRepository, supplier)
    }

    override suspend fun getSupplierByContactInfo(info: String): Flow<MaterialSupplier> {
        TODO("Not yet implemented")
    }

    override suspend fun getSupplierByNameFuzzy(supplierName: String): Flow<MaterialSupplier> {
        val query = Query.query(where("name").like("%$supplierName%").and("deleted").`is`(false))  // fuzzy matching
        return r2dbcEntityTemplate.select(query, MaterialSupplier::class.java).asFlow()
    }

    override suspend fun getAllSupplier(): Flow<MaterialSupplier> {
        TODO("Not yet implemented")
    }

    override suspend fun getSupplierById(id: UUID): MaterialSupplier {
        TODO("Not yet implemented")
    }

}