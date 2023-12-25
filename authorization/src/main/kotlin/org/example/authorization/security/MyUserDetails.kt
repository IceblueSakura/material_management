package org.example.authorization.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class MyUserDetails(
    private val authoritiesStringSet: Set<String>,
    private val password: String,
    private val username: String,
    private val enabled: Boolean,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authoritiesStringSet.map { SimpleGrantedAuthority(it) }
            .toMutableList() // String->GrantedAuthority convert
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}