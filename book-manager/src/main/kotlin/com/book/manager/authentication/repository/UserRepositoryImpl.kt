package com.book.manager.authentication.repository

import com.book.manager.authentication.domain.User
import com.book.manager.infrastructure.database.record.*
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class UserRepositoryImpl(private val mapper: UserMapper) : UserRepository {
    override fun find(email: String): User? {
        val record = mapper.selectOne {
            where(UserDynamicSqlSupport.User.email, isEqualTo(email))
        }
        return record?.let { toModel(it) }
    }

    override fun findByPrimaryKey(id: Long): User?{
        var userRecode = mapper.selectByPrimaryKey(id)
        return userRecode?.let { toModel(it) }
    }

    private fun toModel(record: UserRecord): User {
        return User(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!
        )
    }
}