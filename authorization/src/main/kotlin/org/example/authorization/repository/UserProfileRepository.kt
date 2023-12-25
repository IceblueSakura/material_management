package org.example.authorization.repository

import org.example.authorization.entity.UserProfile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserProfileRepository : CrudRepository<UserProfile, UUID> {
}