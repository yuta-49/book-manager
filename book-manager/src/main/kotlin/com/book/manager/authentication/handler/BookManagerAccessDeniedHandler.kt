package com.book.manager.authentication.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認可失敗時のハンドラー
 */
class BookManagerAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        if (response != null) {
            response.status = HttpServletResponse.SC_FORBIDDEN
        }
    }
}