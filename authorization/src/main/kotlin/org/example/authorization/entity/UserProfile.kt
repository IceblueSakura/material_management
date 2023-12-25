package org.example.authorization.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "user_profile")
data class UserProfile(
    @Id val id: UUID,  // same as UserLogin entity.
)