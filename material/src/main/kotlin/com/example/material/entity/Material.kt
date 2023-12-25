package com.example.material.entity

import com.example.material.utils.MyEntity
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

/**
 * Represents a material.
 *
 * This class is a data class representing a material object. It contains properties such as the material ID, name, description, specification, material type, unit, and deletion status
 *.
 *
 * If value is null,use the type's default value instead of null.(protobuf value cannot be null)
 *
 * @property id The unique identifier for the material.
 * @property materialName The unique name of the material.
 * @property description The optional description for the material.
 * @property materialType The type of the material.
 * @property deleted Whether the material supplier is deleted.
 * @property version The version of the material supplier for optimistic locking.
 */
@Table("material")
data class Material(
    @Id override val id: UUID?,  // database auto-generate
    val materialName: String,
    val description: String,
    val materialType: String,
    val deleted: Boolean = false,
    @Version val version: Long
) : MyEntity<UUID>


