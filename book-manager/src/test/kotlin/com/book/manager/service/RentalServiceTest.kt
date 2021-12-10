package com.book.manager.service

import com.book.manager.authentication.domain.User
import com.book.manager.authentication.repository.UserRepository
import com.book.manager.domain.enum.RoleType
import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.RentalRepository
import com.book.manager.infrastructure.database.service.RentalService
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class RentalServiceTest {
    private val userRepository = mock<UserRepository>()
    private val bookRepository = mock<BookRepository>()
    private val rentalRepository = mock<RentalRepository>()

    private val rentalService = RentalService(userRepository, bookRepository, rentalRepository)

    /**
     * モックオブジェクト関数呼び出し検証
     */
    @Test
    fun `endRental when book is rental then delete to rental`() {
        val userId = 1L
        val bookId = 100L
        val user = User(userId, "test@test.com", "pass", "kotlin", RoleType.USER)
        val book = Book(bookId, "Kotlin入門", "コトリン太郎", LocalDate.now())
        val rental = Rental(bookId, userId, LocalDateTime.now(), LocalDateTime.MAX)
        val bookWithRental = BookWithRental(book, rental)

        whenever(userRepository.find(user.email)).thenReturn(user)
        whenever(bookRepository.findWithRental(any())).thenReturn(bookWithRental)

        rentalService.endRental(bookId, userId)

        verify(userRepository).find(user.email)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository).endRental(bookId)
    }
}