package org.example.authorization.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "rbac_permission")
data class RBACPermission(
    @Id @GeneratedValue val id: Int,
    val name: String,
    val details: String,
    val createdAt: Timestamp,
    @ManyToMany(mappedBy = "permissions")
    val roles: Set<RBACRole>
)