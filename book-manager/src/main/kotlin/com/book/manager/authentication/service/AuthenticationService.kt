package com.book.manager.authentication.service

import com.book.manager.authentication.domain.User
import com.book.manager.authentication.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }
}