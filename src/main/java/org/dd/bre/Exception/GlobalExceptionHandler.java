package org.dd.bre.Exception;

import org.dd.bre.Dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({CartNotFoundException.class, CartItemNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleCartNotFoundException(RuntimeException ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleBadReqException(Exception ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
}
