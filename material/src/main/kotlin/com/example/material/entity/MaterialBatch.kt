package com.example.material.entity

import com.example.material.utils.MyEntity
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.util.*

/**
 * Represents a material batch.
 *
 * This class is used to store information about a material batch, such as its ID, [Material] ID, [MaterialSupplier] ID,
 * employee ID, description, and creation timestamp. Each material batch is uniquely identified by its ID.
 *
 * Relation: One-To-One with [MaterialSupplier], [Material], and [User]
 *
 * @property id The unique ID of the material batch.
 * @property materialId The ID of the corresponding material for this batch.
 * @property supplierId The ID of the supplier for this batch.
 * @property specification The specification of the material.e.g 100g
 * @property description The description of the material batch.
 * @property createAt The creation timestamp of database auto-generate, column must be not null.
 */
@Table("material_batch")
data class MaterialBatch(
    @Id override val id: UUID?,
    val materialId: UUID,  // FOREIGN KEY Cascade Delete
    val supplierId: UUID,
    val specification: String,
    val description: String,
    val createAt: Timestamp?,  // database auto-generate,must be not null
    val deleted: Boolean = false,
    @Version val version: Long
) : MyEntity<UUID>
