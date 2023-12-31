package com.example.material.service.impl

import com.example.material.MaterialDetailsOuterClass
import com.example.material.dto.MaterialDTO
import com.example.material.entity.Material
import com.example.material.exception.ResourceNotFoundException
import com.example.material.repository.MaterialRepository
import com.example.material.service.BatchService
import com.example.material.service.MaterialService
import com.example.material.service.SupplierService
import com.example.material.utils.convertToProtobuf
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
class MaterialServiceImpl(
    @Autowired private val materialRepository: MaterialRepository,
    @Autowired private val batchService: BatchService,
    @Autowired private val supplierService: SupplierService,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : MaterialService {
    @Transactional
    override suspend fun insertMaterial(material: Material): UUID {
        return insertEntity(materialRepository, material)
    }

    @Transactional
    override suspend fun deleteMaterialById(id: UUID): Boolean {
        val material = materialRepository.findById(id)
        return if (material != null) {
            updateEntity(materialRepository, material.copy(deleted = true)) // update existMaterial copy
        } else false
    }

    @Transactional
    override suspend fun updateMaterial(material: Material): Boolean {
        return updateEntity(materialRepository, material)
//        val existingMaterial = materialRepository.findById(material.id!!)
//        return if (existingMaterial != null) {
//            try {
//                materialRepository.save(material.copy(id = existingMaterial.id)) // update material without id
//            } catch (e: OptimisticLockingFailureException) {
//                throw CustomException(
//                    500, "Server Optimistic Locking Failure"
//                )  // convert to customException(probably meaningless)
//            }
//        } else throw ResourceNotFoundException()
    }

    override suspend fun getMaterialById(id: UUID): Material {
        val material = materialRepository.findByIdAndDeleted(id, false)  // only not deleted
            ?: throw ResourceNotFoundException()   // if material is null,throw custom exception.
        return material
    }

    override suspend fun getMaterialByCriteria(criteriaMap: Map<String, String>): Flow<Material> {
        val initialCriteria: Criteria = Criteria.empty()
        return r2dbcEntityTemplate.select(Material::class.java)
            .matching(Query.query(criteriaMap.entries.fold(initialCriteria) { acc, entry ->  // Use fold to accumulate Criteria,use kotlin lambda syntax
                val (key, value) = entry  // 遍历字典，对字典每个键值对解构，acc为累加后的值，以实现追加链式调用
                acc.and(key).`is`(value)  // 不断拼接.and(key).`is`(value)
            })).all().asFlow()
    }

    override suspend fun getAllMaterial(): Flow<Material> {
        return materialRepository.findAllByDeleted(false)  // only not deleted
    }


    override suspend fun getMaterialDetailsById(id: UUID): MaterialDetailsOuterClass.MaterialDetails {
        val material = getMaterialById(id)

        val materialDetails =
            MaterialDetailsOuterClass.MaterialDetails.newBuilder().setMaterial(material.convertToProtobuf())

        batchService.getBatchByMaterialId(material.id!!).collect {  // equal to use loop to read all batches
            materialDetails.addBatchSupplierBuilder().setBatch(it.convertToProtobuf())
                .setSupplier(supplierService.getSupplierById(it.supplierId).convertToProtobuf())
        }

        return materialDetails.build()
    }

    @Transactional
    override suspend fun insertMaterialDTO(material: MaterialDTO): UUID {
        return insertMaterial(
            Material(
                id = null,  // auto generate
                materialName = material.materialName,
                description = material.description,
                materialType = material.materialType,
                version = 0
            )
        )
    }

    @Transactional
    override suspend fun updateMaterialDTO(material: MaterialDTO): Boolean {
        val exist = materialRepository.findById(UUID.fromString(material.id))
        return if (exist != null) {
            updateMaterial(
                exist.copy(
                    materialName = material.materialName,
                    description = material.description,
                    materialType = material.materialType,
                )
            )
        } else false
    }
}