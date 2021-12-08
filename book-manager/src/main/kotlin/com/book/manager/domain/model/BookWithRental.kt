package com.book.manager.domain.model

data class BookWithRental(val book: Book, val rental: Rental?) {

    //拡張プロパティを定義
    val isRental: Boolean
        get() = rental != null
}