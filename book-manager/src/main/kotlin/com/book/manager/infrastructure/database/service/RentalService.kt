package com.book.manager.infrastructure.database.service

import com.book.manager.authentication.repository.UserRepository
import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.RentalRepository
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport
import com.book.manager.infrastructure.database.record.RentalDynamicSqlSupport.Rental.userId
import com.book.manager.infrastructure.database.record.RentalRecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

//貸出期間
private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService(private val userRepository: UserRepository, private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository) {

    /**
     * 貸出機能のロジック
     */
    @Transactional
    fun startRental(bookId: Long, userId: Long){
        //ユーザーのチェック
        userRepository.findByPrimaryKey(userId)?: throw IllegalArgumentException("該当するユーザーが存在しません。 userId:${userId}")

        //本が存在するかチェック
        val book = bookRepository.findWithRental(bookId)?: throw IllegalArgumentException("該当する本が存在しません。bookId:${bookId}")

        //貸出チェック
        if(book.isRental) throw IllegalArgumentException("貸出中です")

        val rentalDateTime = LocalDateTime.now()
        val returnDeadline = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(bookId, userId, rentalDateTime, returnDeadline)

        rentalRepository.startRental(rental)
    }

    /**
     * 返却機能のロジック
     */
    @Transactional
    fun endRental(bookId: Long, userId: Long){
        //ユーザーが存在するかチェック
        userRepository.findByPrimaryKey(userId) ?: throw IllegalArgumentException("該当するユーザーが存在しません userId:${userId}")

        //本が存在するかチェック
        val book = bookRepository.findWithRental(bookId)?: throw IllegalArgumentException("該当する本が存在しません。bookId:${bookId}")

        //貸出中か確認する
        if(!book.isRental){ throw IllegalArgumentException("未貸出の商品です。bookId:${bookId}")}
        if(book.rental!!.userId != userId){ throw IllegalArgumentException("未貸出の商品です。bookId:${bookId}")}

        rentalRepository.endRental(bookId)
    }
}