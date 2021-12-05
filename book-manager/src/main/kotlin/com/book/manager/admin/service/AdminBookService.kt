package com.book.manager.admin.service

import com.book.manager.domain.model.Book
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.RowId
import java.time.LocalDate

@Service
class AdminBookService(private val bookRepository: BookRepository) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id) ?.let {
            throw IllegalArgumentException("既に存在する書籍ID： ${book.id}")
        }

        //書籍を登録する
        bookRepository.register(book)
    }

    @Transactional
    fun update(bookId: Long, title: String?, author: String?, releaseDate: LocalDate?){
        bookRepository.findWithRental(bookId) ?:
            throw IllegalArgumentException("存在しない書籍ID： ${bookId}")


        bookRepository.update(bookId, title, author, releaseDate)
    }

    @Transactional
    fun delete(bookId: Long){
        bookRepository.delete(bookId)
    }
}