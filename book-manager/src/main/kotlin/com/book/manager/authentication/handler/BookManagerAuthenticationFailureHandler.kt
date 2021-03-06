package com.book.manager.authentication.handler

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認証失敗時のハンドラー
 */
class BookManagerAuthenticationFailureHandler: AuthenticationFailureHandler {
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?,
        exception: AuthenticationException?) {
        val logger = LoggerFactory.getLogger(BookManagerAuthenticationFailureHandler::class.java)

        if (response != null) {
            logger.debug("UNAUTHORIZED,uri:{}", request?.requestURI, exception)
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }

}