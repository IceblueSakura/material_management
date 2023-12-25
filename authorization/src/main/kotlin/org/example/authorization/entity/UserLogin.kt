package org.example.authorization.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.util.*


/**
 * Represents a user login entity .
 *
 * @property id The unique identifier of the user login. Link to UserProfile entity(one-to-one),only generated here.
 * @property username The unique username of the user.
 * @property password The password of the user. Bcrypt is usually used. e.g "123456"->{bcrypt}$2a$10$ks9dYqj34BnCIqcgwBzegeRDYu2pcnwkv7NIFveN2ssKk/qNGI4rO
 * @property enabled Indicates whether the user is enabled or disabled.
 * @property createdAt The timestamp indicating the creation date and time of the user.
 * @property lastLogin The timestamp indicating the last login date and time of the user.
 * @property roles The set of RBAC roles associated with the user login (many-to-many relationship).
 *
 * @see RBACRole
 */
@Entity
@Table(name = "user_login", indexes = [Index(columnList = "username")])
data class UserLogin(
    @Id @GeneratedValue val id: UUID?,
    @Column(unique = true) val username: String,
    val password: String,
    val enabled: Boolean,
    val deleted: Boolean,  // if user is deleted,the column will be set true
    @Column(name = "create_at") val createdAt: Timestamp,
    @Column(name = "last_login") val lastLogin: Timestamp,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE]) @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    ) val roles: Set<RBACRole>, // many-to-many
)
