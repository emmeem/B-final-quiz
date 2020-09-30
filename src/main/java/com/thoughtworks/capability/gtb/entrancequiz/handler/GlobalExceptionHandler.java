package com.thoughtworks.capability.gtb.entrancequiz.handler;

import com.thoughtworks.capability.gtb.entrancequiz.error.ErrorResult;
import com.thoughtworks.capability.gtb.entrancequiz.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler({TraineeIsNotExistException.class, TrainerIsNotExistException.class, GroupIsNotExistException.class })
    public ResponseEntity<ErrorResult> existHandler(Exception ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler({GroupingException.class, GroupIsRepeatException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResult> groupHandler(Exception ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
