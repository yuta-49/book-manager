package com.book.manager.admin.controller

import com.book.manager.admin.service.AdminBookService
import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import com.book.manager.presentation.form.RegisterBookRequest
import com.book.manager.presentation.form.UpdateBookRequest
import org.mybatis.dynamic.sql.SqlBuilder.update
import org.springframework.web.bind.annotation.*
import java.sql.RowId

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class AdminBookController(private val adminBookService: AdminBookService) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterBookRequest) {
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }

    @PutMapping("/update")
    fun update(@RequestBody request: UpdateBookRequest) {
        adminBookService.update(request.id, request.title, request.author, request.releaseDate)
    }

    @DeleteMapping("/delete/{book_id}")
    fun delete(@PathVariable("book_id") bookId: Long){
        adminBookService.delete(bookId)
    }
}