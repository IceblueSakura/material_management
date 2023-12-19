package com.example.material.service

import com.example.material.MaterialDetailsOuterClass
import com.example.material.MaterialOuterClass
import com.example.material.entity.Material
import kotlinx.coroutines.flow.Flow
import java.util.*

interface MaterialService {
    suspend fun insertMaterial(material: Material): Boolean

    /**
     * Deletes a material by its ID.
     *
     * This method deletes a material from the database based on its ID. The material `deleted` column has been set to false.Do NOT remove from the database.
     *
     * @param id The ID of the material to delete.
     * @return True if the material `delete` column has been set to false, false otherwise.
     */
    suspend fun deleteMaterialById(id: UUID): Boolean
    suspend fun updateMaterial(material: Material): Material
    suspend fun getMaterialById(id: UUID): Material

    /**
     * Retrieves materials based on the given criteria.
     *
     * This method retrieves materials from the database that match the given criteria. The criteria parameter allows specifying various filtering options to narrow down the search
     * results.
     * The method returns a [Flow] of [Material] objects that match the specified criteria.
     *
     * @param criteria The criteria used to filter the materials. It can include properties such as material name, material type, unit, etc.
     * @return A [Flow] of [Material] objects that match the specified criteria.
     */
    suspend fun getMaterialByCriteria(criteriaMap: Map<String, String>): Flow<Material>
    suspend fun getAllMaterial(): Flow<Material>


    /**
     * Retrieves a list of materials. Provide to Controller.
     *
     * This function retrieves a list of materials by returning a [Flow] of [MaterialOuterClass.Material] objects. The materials are retrieved from the database and converted to protobuf
     * format using the MaterialOuterClass.Material class.
     *
     * @return A [Flow] of [MaterialOuterClass.Material] objects representing the list of materials.
     */
    suspend fun getMaterialList(): Flow<MaterialOuterClass.Material>

    /**
     * Retrieves the details of a material based on its ID. Provide to Controller.
     *
     * This method retrieves the details of a material from the database based on its ID. The method returns an instance
     * of [MaterialDetailsOuterClass.MaterialDetails] which contains information such as the material ID, name,
     * description, specification, material type, and unit.
     *
     * @param id The ID of the material.
     * @return An instance of [MaterialDetailsOuterClass.MaterialDetails] representing the details of the material.
     */
    suspend fun getMaterialDetailsById(id: UUID): MaterialDetailsOuterClass.MaterialDetails
}