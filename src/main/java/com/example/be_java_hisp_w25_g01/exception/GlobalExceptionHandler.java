package com.example.be_java_hisp_w25_g01.exception;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
        MessagesDTO messagesDTO = new MessagesDTO(ex.getFieldError().getDefaultMessage().toString());
        return new ResponseEntity<>(messagesDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> handleValidationExceptions(HttpMessageNotReadableException ex){
        MessagesDTO messagesDTO = new MessagesDTO(ex.getMessage());
        return new ResponseEntity<>(messagesDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestExceotion(BadRequestException e){
        MessagesDTO exceptionDto = new MessagesDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e){
        MessagesDTO exceptionDto = new MessagesDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);

    }
}
