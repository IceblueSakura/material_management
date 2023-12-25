package org.example.authorization.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "rbac_role")
data class RBACRole(
    @Id val id: Int,
    val name: String,
    val details: String,
    val createdAt: Timestamp,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "rbac_role_permission",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id", referencedColumnName = "id")]
    )
    val permissions: Set<RBACPermission>,
    @ManyToMany(mappedBy = "roles")
    val users: Set<UserLogin>
)