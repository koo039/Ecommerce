package org.dd.bre.Exception;

import org.dd.bre.Dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({
            CartNotFoundException.class,
            CartItemNotFoundException.class,
            ProductNotFoundException.class,
            UserNotFoundException.class,
            WishListNotFoundException.class,
            ProductVariantNotFoundException.class,
            AddressNotFoundException.class,
            CategoryNotFoundException.class,
    })
    public ResponseEntity<ErrorDetails> handleNotFoundException(RuntimeException ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorDetails> handleConflictException(UserAlreadyExists ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);

    }

    @ExceptionHandler({
            PasswordNotMatch.class,
            InvalidPaymentMethodException.class,
            InvalidOrderException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorDetails> handleBadRequestException(PasswordNotMatch ex)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleUnexpectedException(Exception ex, WebRequest request)
    {
        ErrorDetails errorResponse = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
