package project.io.app.common.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import project.io.app.core.merchant.exception.FileDownloadFailedException
import project.io.app.core.merchant.exception.FileUploadFailedException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [FileUploadFailedException::class, FileDownloadFailedException::class])
    fun resolveFileException(exception: RuntimeException): ErrorResponse {
        return ErrorResponse(500, exception.message ?: "파일 처리 중 오류가 발생했습니다.")
    }

    @ExceptionHandler(value = [Exception::class])
    fun resolveException(exception: Exception): ErrorResponse {
        return ErrorResponse(500, "서버 내부 오류입니다.")
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String,
)
