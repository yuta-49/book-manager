package com.book.manager.authentication.domain

import com.book.manager.domain.enum.RoleType

data class User (
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
    )

