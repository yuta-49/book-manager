package com.book.manager.application.service

import com.book.manager.domain.model.BookWithRental
import com.book.manager.domain.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {
//    val logger = LoggerFactory.getLogger(BookService::class.java)

    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Long): BookWithRental {
        return  bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しない書籍ID: $bookId")
    }
}