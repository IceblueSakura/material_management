package com.example.material.service

import com.example.material.dto.MatchSupplierDTO
import com.example.material.entity.MaterialSupplier
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SupplierService {

    /**
     * Inserts a material supplier into the database.
     *
     * This method is used to insert a new material supplier into the database. The supplier's name,
     * description, contact information, and version must be provided. The supplier will be marked as
     * not deleted and assigned a unique ID. The ID of the inserted supplier will be returned.
     *
     * @param supplier The material supplier to be inserted.
     * @return The ID of the inserted material supplier.
     */
    suspend fun insertSupplier(supplier: MaterialSupplier): UUID

    /**
     * Deletes a material supplier from the database by its ID.
     *
     * This method is used to delete a material supplier from the database based on its unique ID.
     * The supplier will be marked as deleted in the database.
     *
     * @param id The ID of the material supplier to be deleted.
     * @return true if the supplier is successfully deleted, false otherwise.
     */
    suspend fun deleteSupplierById(id: UUID): Boolean

    /**
     * Updates the information of a material supplier.Can't update id (is primary key).
     *
     * This method is used to update the information of a material supplier in the database.
     * The supplier's name, description, and contact information can be modified.
     *
     * @return true if the supplier is successfully updated, false otherwise.
     */
    suspend fun updateSupplier(supplier: MaterialSupplier): Boolean


    /**
     * Retrieves material suppliers from the database based on a fuzzy match of their names.
     *
     * This method is used to retrieve material suppliers from the database whose names match the given
     * search string in a fuzzy manner. The search is case-insensitive and uses a fuzzy matching algorithm.
     * The method returns a flow of MaterialSupplier objects that match the search criteria.
     *
     * @param supplierName The supplierName to search for, using a fuzzy matching algorithm.e.g "hello" can match "hello!" and "hello world"
     * @return A flow of MaterialSupplier objects that match the search criteria.
     */
    suspend fun getSupplierByNameFuzzy(supplierName: String): Flow<MaterialSupplier>
    suspend fun getAllSupplier(): Flow<MaterialSupplier>
    suspend fun getSupplierById(id: UUID): MaterialSupplier

    suspend fun insertSupplierDTO(supplierDTO: MatchSupplierDTO): UUID

    suspend fun updateSupplierDTO(supplierDTO: MatchSupplierDTO): Boolean
}