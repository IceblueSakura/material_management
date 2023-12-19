package com.example.material.service.impl

import com.example.material.MaterialDetailsOuterClass
import com.example.material.MaterialOuterClass
import com.example.material.entity.Material
import com.example.material.exception.CustomException
import com.example.material.exception.ResourceNotFoundException
import com.example.material.repository.MaterialRepository
import com.example.material.service.BatchService
import com.example.material.service.MaterialService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import java.util.*

@Service
class MaterialServiceImpl(
    @Autowired private val materialRepository: MaterialRepository,
    @Autowired private val batchService: BatchService,
    @Autowired private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : MaterialService {

    override suspend fun insertMaterial(material: Material): Boolean {
        return try {
            materialRepository.save(material.copy(id = null)) // auto generate UUID primary key
            true
        } catch (e: OptimisticLockingFailureException) {
            false
        }
    }

    override suspend fun deleteMaterialById(id: UUID): Boolean {
        val material = materialRepository.findById(id)
        return if (material != null) {
            try {
                materialRepository.save(material.copy(deleted = true)) // update existMaterial copy
                true
            } catch (e: OptimisticLockingFailureException) {
                false
            }
        } else false
    }

    override suspend fun updateMaterial(material: Material): Material {
        val existingMaterial = materialRepository.findById(material.id!!)
        return if (existingMaterial != null) {
            try {
                materialRepository.save(material.copy(id = existingMaterial.id)) // update material without id
            } catch (e: OptimisticLockingFailureException) {
                throw CustomException(
                    500,
                    "Server Optimistic Locking Failure"
                )  // convert to customException(probably meaningless)
            }
        } else throw ResourceNotFoundException()
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

    override suspend fun getMaterialList(): Flow<MaterialOuterClass.Material> {
        TODO("Not yet implemented")
    }

    override suspend fun getMaterialDetailsById(id: UUID): MaterialDetailsOuterClass.MaterialDetails {
        val material = getMaterialById(id)

        TODO("Not yet implemented")
    }
}