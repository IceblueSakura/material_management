package com.example.material.entity

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
 * @property id The unique ID of the material supplier.
 * @property name The name of the material supplier.
 * @property description An optional description of the material supplier.
 * @property contactInformation The contact information of the material supplier.
 * @property deleted Whether the material supplier is deleted.
 * @property version The version of the material supplier for optimistic locking.
 */
@Table("material_supplier")
data class MaterialSupplier(
    @Id val id: UUID,
    val supplierName: String,
    val description: String?,
    val contactInformation: String,
    val deleted: Boolean = false,
    @Version val version:Long
)