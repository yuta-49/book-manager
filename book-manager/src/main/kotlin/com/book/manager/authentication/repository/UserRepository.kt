package com.book.manager.authentication.repository

import com.book.manager.authentication.domain.User

interface UserRepository {
    fun find(email: String): User?
}