package com.book.manager.authentication.handler

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認可失敗時のハンドラー
 */
class BookManagerAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest?,  response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?) {
        val logger = LoggerFactory.getLogger(BookManagerAccessDeniedHandler::class.java)

        if(response != null){
            logger.debug("UNAUTHORIZED,uri:{}", request?.requestURI, accessDeniedException)
            response.status = HttpServletResponse.SC_FORBIDDEN
        }
    }
}