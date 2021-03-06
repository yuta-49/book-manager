package com.book.manager.infrastructure.database.mapper.custom

import com.book.manager.infrastructure.database.record.BookDynamicSqlSupport.Book
import com.book.manager.infrastructure.database.record.BookDynamicSqlSupport.Book.author
import com.book.manager.infrastructure.database.record.BookDynamicSqlSupport.Book.id
import com.book.manager.infrastructure.database.record.BookDynamicSqlSupport.Book.releaseDate
import com.book.manager.infrastructure.database.record.BookDynamicSqlSupport.Book.title
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport.Rental
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport.Rental.rentalDatetime
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport.Rental.returnDeadline
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport.Rental.userId
import com.book.manager.infrastructure.database.record.BookWithRentalRecord
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.from


private val columnList = listOf(
    id,
    title,
    author,
    releaseDate,
    userId,
    rentalDatetime,
    returnDeadline
)

fun BookWithRentalMapper.select(): List<BookWithRentalRecord> {
    val selectStatement = select(columnList).from(Book, "b") {
        leftJoin(Rental, "r") {
            on(id, equalTo(Rental.bookId))
        }
    }
    return selectMany(selectStatement)
}

fun BookWithRentalMapper.selectByPrimaryKey(id_: Long): BookWithRentalRecord?{
    val selectStatement = select(columnList).from(Book, "b") {
        leftJoin(Rental, "r") {
            on(id, equalTo(Rental.bookId))
        }
        where(id, isEqualTo(id_))
    }

    return selectOne(selectStatement)
}