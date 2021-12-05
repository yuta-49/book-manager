package com.book.manager.authentication.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認証成功時のハンドラー
 */
class BookManagerAuthenticationSuccessHandler: AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        if (response != null) {
            response.status = HttpServletResponse.SC_OK
        }
    }
}