/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.book.manager.infrastructure.database.record

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.SqlTable

object RentalDynamicSqlSupport {
    object Rental : SqlTable("rental") {
        val bookId = column<Long>("book_id", JDBCType.BIGINT)

        val userId = column<Long>("user_id", JDBCType.BIGINT)

        val rentalDatetime = column<LocalDateTime>("rental_datetime", JDBCType.TIMESTAMP)

        val returnDatetime = column<LocalDateTime>("return_datetime", JDBCType.TIMESTAMP)
    }
}