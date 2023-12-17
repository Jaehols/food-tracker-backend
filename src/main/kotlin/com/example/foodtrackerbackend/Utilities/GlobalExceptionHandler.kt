import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
            ex: HttpMessageNotReadableException,
            request: WebRequest
    ): ResponseEntity<Any> {
        val bodyOfResponse = "Invalid JSON format or structure"
        val responseEntity = handleExceptionInternal(
                ex,
                bodyOfResponse,
                HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        )
        return responseEntity ?: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
}
