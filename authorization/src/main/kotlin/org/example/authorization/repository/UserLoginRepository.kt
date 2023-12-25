package org.example.authorization.repository

import org.example.authorization.entity.UserLogin
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserLoginRepository : CrudRepository<UserLogin, UUID> {
    fun findByUsername(username: String): UserLogin
}