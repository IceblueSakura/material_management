package org.example.authorization.service

import org.example.authorization.entity.UserLogin
import org.example.authorization.entity.UserProfile
import org.example.authorization.security.MyUserDetails
import java.util.*

interface UserService {
    fun getUserDetailsByUsername(username: String): MyUserDetails

    fun getUserLoginByUsername(): UserLogin
    fun getUserProfileById(id: UUID): UserProfile
}