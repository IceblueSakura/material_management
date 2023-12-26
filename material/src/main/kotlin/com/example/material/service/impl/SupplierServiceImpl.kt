package com.example.material.service.impl

import com.example.material.dto.MatchSupplierDTO
import com.example.material.entity.MaterialSupplier
import com.example.material.exception.CustomException
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
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class SupplierServiceImpl(
    @Autowired private val supplierRepository: MaterialSupplierRepository,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : SupplierService {

    @Transactional
    override suspend fun insertSupplier(supplier: MaterialSupplier): UUID {
        return insertEntity(supplierRepository, supplier)
    }

    @Transactional
    override suspend fun deleteSupplierById(id: UUID): Boolean {
        val supplier = supplierRepository.findById(id)
        return if (supplier != null) {
            updateEntity(supplierRepository, supplier.copy(deleted = true)) // update existBatch copy
        } else false
    }

    @Transactional
    override suspend fun updateSupplier(supplier: MaterialSupplier): Boolean {
        return updateEntity(supplierRepository, supplier)
    }


    override suspend fun getSupplierByNameFuzzy(supplierName: String): Flow<MaterialSupplier> {
        val query = Query.query(where("name").like("%$supplierName%").and("deleted").`is`(false))  // fuzzy matching
        return r2dbcEntityTemplate.select(query, MaterialSupplier::class.java).asFlow()
    }

    override suspend fun getAllSupplier(): Flow<MaterialSupplier> {
        return supplierRepository.findAllByDeleted(deleted = false)
    }

    override suspend fun getSupplierById(id: UUID): MaterialSupplier {
        val exist = supplierRepository.findByIdAndDeleted(id, deleted = false)
        return exist ?: throw CustomException(404, "Supplier Not Found")
    }

    @Transactional
    override suspend fun insertSupplierDTO(supplierDTO: MatchSupplierDTO): UUID {
        return insertSupplier(
            MaterialSupplier(
                id = null,  // auto generate
                supplierName = supplierDTO.supplierName,
                description = supplierDTO.description,
                contactInfo = supplierDTO.contactInfo,
                version = 0
            )
        )
    }

    @Transactional
    override suspend fun updateSupplierDTO(supplierDTO: MatchSupplierDTO): Boolean {
        val exist = supplierRepository.findById(UUID.fromString(supplierDTO.id))
        return if (exist != null) {
            updateSupplier(
                // only modified change value
                exist.copy(
                    supplierName = supplierDTO.supplierName,
                    description = supplierDTO.description,
                    contactInfo = supplierDTO.contactInfo,
                )
            )
        } else false
    }

}