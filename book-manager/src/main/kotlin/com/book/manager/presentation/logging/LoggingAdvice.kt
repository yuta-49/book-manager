package com.book.manager.presentation.logging

import com.book.manager.authentication.service.BookManagerUserDetails
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LoggingAdvice {
    private val logger = LoggerFactory.getLogger(LoggingAdvice::class.java)

    //メソッド実行前に呼び出す
    @Before("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun beforeLog(joinPoint: JoinPoint) {
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        logger.info("------------------------------------------------")
        logger.info("Start: ${joinPoint.signature} userId=${user.id}")
        logger.info("Class: ${joinPoint.target.javaClass}")
        logger.info("Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}")
        logger.info("------------------------------------------------")
    }

    //メソッド実行後に呼び出す
    @After("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun afterLog(joinPoint: JoinPoint){
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        logger.info("------------------------------------------------")
        logger.info("End: ${joinPoint.signature} userId=${user.id}")
        logger.info("------------------------------------------------")
    }

}