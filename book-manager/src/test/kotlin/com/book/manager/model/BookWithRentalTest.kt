package com.book.manager.model

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import com.book.manager.domain.model.Rental
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * ドメインオブジェクトのテスト
 */
class BookWithRentalTest {
    @Test
    fun `isRental when rental is null the return false`(){
        val book = Book(1, "kotlin入門", "コトリン入門", LocalDate.now())
        val bookWithRental = BookWithRental(book, null)
        Assertions.assertThat(bookWithRental.isRental).isEqualTo(false)
    }

    @Test
    fun `isRental when rental is not null then return true`(){
        val book = Book(2, "Java入門", "Java入門", LocalDate.now())
        val rental = Rental(2, 2, LocalDateTime.now(), LocalDateTime.MAX)
        val bookWithRental = BookWithRental(book, rental)
        Assertions.assertThat(bookWithRental.isRental).isEqualTo(true)
    }
}