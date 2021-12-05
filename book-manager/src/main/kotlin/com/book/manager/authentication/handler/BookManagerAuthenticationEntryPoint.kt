package com.book.manager.authentication.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 未認証時のハンドラー
 */
class BookManagerAuthenticationEntryPoint : AuthenticationEntryPoint{
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        if (response != null) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }
}