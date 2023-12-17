package com.example.material.repository

import com.example.material.entity.Material
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Repository interface for accessing and modifying materials in the database.
 *
 * This interface extends the `CoroutineCrudRepository` interface, which provides basic CRUD operations for the `Material` entity.
 * It allows for asynchronous operations using coroutines.
 * More complex operations are implemented in services.Such as src/service/impl/MaterialServiceImpl.kt
 * @see Material
 * @see CoroutineCrudRepository
 */
@Repository
interface MaterialRepository : CoroutineCrudRepository<Material, UUID> {
    suspend fun findByIdAndDeleted(id: UUID, deleted: Boolean): Material?
    suspend fun findAllByDeleted(deleted: Boolean): Flow<Material>

    /**
     * Finds a material by its name.
     *
     * This method searches for a material in the database using the specified name.
     *
     * @param name The name of the material to search for.
     * @return The found material, or null if no material with the specified name exists.
     */
    suspend fun findByMaterialName(name: String): Material?
}