package com.example.be_java_hisp_w25_g01.exception;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController  {
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
}
