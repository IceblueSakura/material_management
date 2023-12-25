package org.example.authorization.security

import org.example.authorization.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailsServiceImpl(@Autowired private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("username is null")  // may occur(after all, UserDetailsService is Java Class )
        }
        return userService.getUserDetailsByUsername(username)  // maybe throw UsernameNotFoundException
    }
}