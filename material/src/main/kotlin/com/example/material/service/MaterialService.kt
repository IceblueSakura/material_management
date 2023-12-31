package com.example.material.service

import com.example.material.MaterialDetailsOuterClass
import com.example.material.dto.MaterialDTO
import com.example.material.entity.Material
import kotlinx.coroutines.flow.Flow
import java.util.*

interface MaterialService {
    suspend fun insertMaterial(material: Material): UUID

    /**
     * Deletes a material by its ID.
     *
     * This method deletes a material from the database based on its ID. The material `deleted` column has been set to false.Do NOT remove from the database.
     *
     * @param id The ID of the material to delete.
     * @return True if the material `delete` column has been set to false, false otherwise.
     */
    suspend fun deleteMaterialById(id: UUID): Boolean

    /**
     * Updates a material in the database.Can't update id (is primary key).
     *
     * This method updates a material in the database based on the provided [Material] object. The material is updated with the new values for its properties, including material name
     *, description, specification, material type, inventory, and version. The updated material is returned after the operation is completed.
     *
     * @param material The [Material] object representing the material to be updated.
     * @return The updated status.
     */
    suspend fun updateMaterial(material: Material): Boolean
    suspend fun getMaterialById(id: UUID): Material

    /**
     * Retrieves materials based on the given criteria.
     *
     * This method retrieves materials from the database that match the given criteria. The criteria parameter allows specifying various filtering options to narrow down the search
     * results.
     * The method returns a [Flow] of [Material] objects that match the specified criteria.
     *
     * @param criteriaMap The criteria used to filter the materials. It can include properties such as material name, material type, unit, etc.
     * @return A [Flow] of [Material] objects that match the specified criteria.
     */
    suspend fun getMaterialByCriteria(criteriaMap: Map<String, String>): Flow<Material>

    /**
     * Retrieves all materials.
     *
     * This method retrieves all materials from the database and returns them as a flow of Material objects.
     *
     * @return A flow of Material objects representing all materials in the database.
     */
    suspend fun getAllMaterial(): Flow<Material>


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

    suspend fun insertMaterialDTO(material: MaterialDTO): UUID
    suspend fun updateMaterialDTO(material: MaterialDTO): Boolean
}