package com.book.manager.infrastructure.database.repository

import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.RentalRepository
import com.book.manager.infrastructure.database.record.RentalMapper
import com.book.manager.infrastructure.database.record.RentalRecord
import com.book.manager.infrastructure.database.record.*
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class RentalRepositoryImpl(private val rentalMapper: RentalMapper): RentalRepository {
    /**
     * 貸出処理
     */
    override fun startRental(rental: Rental) {
        rentalMapper.insert(toRecord(rental))
    }

    /**
     * modelをデータクラスに変換する
     */
    private fun toRecord(model: Rental): RentalRecord{
        return RentalRecord(model.bookId, model.userId, model.rentalDatetime, model.returnDeadline)
    }
}