package com.example.material.entity

import com.example.material.utils.MyEntity
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/**
 * Represents a material supplier.
 *
 * This class is used to store information about a material supplier, such as its name, description,
 * contact information, and whether it is deleted. Each material supplier is uniquely identified
 * by an ID. The version field is used for optimistic locking when performing updates.
 *
 * Relation: Many-To-Many with [MaterialBatch]
 *
 * @property id The unique ID of the material supplier.
 * @property supplierName The unique name of the material supplier.
 * @property description An optional description of the material supplier.
 * @property contactInfo The contact information of the material supplier.
 * @property deleted Whether the material supplier is deleted.
 * @property version The version of the material supplier for optimistic locking.
 */
@Table("material_supplier")
data class MaterialSupplier(
    @Id override val id: UUID?,
    val supplierName: String,  // unique
    val description: String,
    val contactInfo: String,
    val deleted: Boolean = false,
    @Version val version: Long
) : MyEntity<UUID>