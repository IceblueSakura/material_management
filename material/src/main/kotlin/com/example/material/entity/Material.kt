package com.example.material.entity

import com.example.material.MaterialOuterClass
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

/**
 * Represents a material.
 *
 * This class is a data class representing a material object. It contains properties such as the material ID, name, description, specification, material type, unit, and deletion status
 *.
 *
 * If value is null,use the type's default value instead of null.(protobuf value can not be null)
 *
 * @property id The unique identifier for the material.
 * @property materialName The unique name of the material.
 * @property description The optional description for the material.
 * @property specification The specification of the material.e.g 100g
 * @property materialType The type of the material.
 * @property inventory Inventory of material.
 * @property deleted Whether the material supplier is deleted.
 * @property version The version of the material supplier for optimistic locking.
 */
@Table("material")
data class Material(
    @Id val id: UUID?,  // database auto-generate
    val materialName: String,
    val description: String,
    val specification: String,
    val materialType: String,
    val inventory: Int,
    val deleted: Boolean = false,
    @Version val version: Long
)


