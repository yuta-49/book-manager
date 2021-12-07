package com.book.manager.presentation.log

import com.book.manager.authentication.service.BookManagerUserDetails
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
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
        logger.info("①")
        logger.info("Start: ${joinPoint.signature} userId=${user.id}")
        logger.info("------------------------------------------------")
        logger.info("Class: ${joinPoint.target.javaClass}")
        logger.info("------------------------------------------------")
        logger.info("Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}")
        logger.info("------------------------------------------------")
    }

    //メソッド実行後に呼び出す
    @After("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun afterLog(joinPoint: JoinPoint){
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        logger.info("②")
        logger.info("End: ${joinPoint.signature} userId=${user.id}")
        logger.info("------------------------------------------------")
    }

    //AOP対象の処理を実行
    @Around("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun aroundLog(joinPoint: ProceedingJoinPoint): Any?{
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        //前処理
        logger.info("③")
        logger.info("Start: ${joinPoint.signature} userId=${user.id}")
        logger.info("------------------------------------------------")

        //本処理の実装
        val result = joinPoint.proceed()

        logger.info("④")
        logger.info("End Proceed: ${joinPoint.signature} userId=${user.id}")
        logger.info("------------------------------------------------")

        //本処理の結果の返却
        return result
    }

    //メソッドの戻り値を出力
    @AfterReturning("execution(* com.book.manager.presentation.controller..*.*(..))", returning = "returnValue")
    fun afterReturningLog(joinPoint: JoinPoint, returnValue: Any?){
        logger.info("⑤")
        logger.info("End: ${joinPoint.signature} returnValue=${returnValue}")
        logger.info("------------------------------------------------")
    }

    //例外処理を出力
    @AfterThrowing("execution(* com.book.manager.presentation.controller..*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: Throwable){
        logger.info("⑥")
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
        logger.info("------------------------------------------------")
    }
}