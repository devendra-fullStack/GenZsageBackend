package xyz.genzsage.genzsagebackend.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.genzsage.genzsagebackend.dto.GlobalResponseDto;


import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public GlobalResponseDto<?> handleAppException(AppException exception) {
        return GlobalResponseDto.builder()
                .success(false)
                .statusCode(exception.getHttpStatus().value())
                .data(null)
                .message(exception.getMessage())
                .errors(List.of(exception.getMessage()))
                .build();
    }
}
