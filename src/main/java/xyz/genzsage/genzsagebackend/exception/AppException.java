package xyz.genzsage.genzsagebackend.exception;


import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException{
private final HttpStatus httpStatus;

    public AppException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
