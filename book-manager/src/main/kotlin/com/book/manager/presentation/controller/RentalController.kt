package com.book.manager.presentation.controller

import com.book.manager.authentication.service.BookManagerUserDetails
import com.book.manager.infrastructure.database.repository.RentalService
import com.book.manager.presentation.form.RentalStartRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rental")
@CrossOrigin
class RentalController(private val rentalService: RentalService) {

    @PostMapping("/start")
    fun startRental(@RequestBody request: RentalStartRequest) {
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        rentalService.startRental(request.bookId, user.id)
    }
}