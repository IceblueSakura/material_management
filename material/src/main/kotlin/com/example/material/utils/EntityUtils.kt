package com.example.material.utils

import com.example.material.exception.CustomException
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 * Inserts an entity into the provided repository.
 *
 * This function inserts the [entity] into the specified [repository] using the repository's saved method.
 * If the entity is successfully saved, the function returns the ID of the saved entity.
 *
 * @param repository Database entity repository. Must implement the [CoroutineCrudRepository] interface.
 * @param entity The entity to be inserted. If you need auto generate UUID primary key, set id=null.
 * @return The ID of the saved entity.
 * @throws CustomException If an error occurs during the save operation.
 */
suspend fun <T : MyEntity<ID>, ID> insertEntity(repository: CoroutineCrudRepository<T, ID>, entity: T): ID {
    return try {
        val save = repository.save(entity) // if you need auto generate UUID primary key,set id=null.
        save.id ?: throw CustomException(500, "no reason Exception")  // save should not be null
    } catch (e: OptimisticLockingFailureException) {
        throw CustomException(500, "OptimisticLockingFailureException")
    } catch (e: IllegalArgumentException) {
        throw CustomException(400, "Entity is null")
    }
}

/**
 * Updates an entity in the specified repository.
 *
 * This function updates an entity in the provided [repository]. The entity is updated with the new values for its properties.
 * If the update is successful, the function returns `true`. If the update fails due to an optimistic locking failure,
 * the function returns `false`.
 *
 * @param repository Database entity repository. Must implement the [CoroutineCrudRepository] interface.
 * @param entity The entity to be updated. if delete, set deleted = true
 * @return `true` if the update is successful, `false` if an optimistic locking failure occurs.
 */
suspend fun <T : MyEntity<ID>, ID> updateEntity(repository: CoroutineCrudRepository<T, ID>, entity: T): Boolean {
    return try {
        repository.save(entity) // update existEntity copy
        true
    } catch (e: OptimisticLockingFailureException) {
        false  // throw CustomException ?
    }
}

interface MyEntity<ID> {
    val id: ID?
}