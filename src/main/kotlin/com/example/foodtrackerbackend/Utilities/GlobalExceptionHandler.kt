import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.converter.HttpMessageNotReadableException
import reactor.core.publisher.Mono

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): Mono<ResponseEntity<Any>> {
        val bodyOfResponse = "Invalid JSON format or structure"
        return Mono.just(ResponseEntity(bodyOfResponse, HttpStatus.BAD_REQUEST))
    }
}

