package org.example.authorization.service.impl

import org.example.authorization.entity.UserLogin
import org.example.authorization.entity.UserProfile
import org.example.authorization.repository.UserLoginRepository
import org.example.authorization.repository.UserProfileRepository
import org.example.authorization.security.MyUserDetails
import org.example.authorization.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    @Autowired private val userLoginRepository: UserLoginRepository,
    @Autowired private val userProfileRepository: UserProfileRepository
) : UserService {
    override fun getUserDetailsByUsername(username: String): MyUserDetails {
        val userLogin = userLoginRepository.findByUsername(username)
        return MyUserDetails(
            userLogin.roles.map { it.name }.toSet(), userLogin.password, userLogin.username, userLogin.enabled
        )
    }

    override fun getUserLoginByUsername(): UserLogin {
        TODO("Not yet implemented")
    }

    override fun getUserProfileById(id: UUID): UserProfile {
        TODO("Not yet implemented")
    }
}