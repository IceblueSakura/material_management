package com.example.material.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.util.*

/**
 * Represents a material batch.
 *
 * This class is used to store information about a material batch, such as its ID, material ID, supplier ID,
 * employee ID, description, and creation timestamp. Each material batch is uniquely identified by its ID.
 *
 * Relation: One-To-One with [MaterialSupplier], [Material], and [User]
 *
 * @property id The unique ID of the material batch.
 * @property materialId The ID of the corresponding material for this batch.
 * @property supplierId The ID of the supplier for this batch.
 * @property employeeId The ID of the employee associated with this batch.
 * @property description The description of the material batch.
 * @property createAt The creation timestamp of the material batch.Database auto-generate
 */
@Table("material_batch")
data class MaterialBatch(
    @Id val id: UUID,
    val materialId: UUID,  // FOREIGN KEY Cascade Delete
    val supplierId: UUID,
    val employeeId: UUID,
    val description: String,
    val createAt: Timestamp?,  // database auto-generate
)
