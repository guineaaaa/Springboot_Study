package com.apple.shopExample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handler(){
            return ResponseEntity.status(400).body("에러발생");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handler1(){
        return ResponseEntity.status(400).body("에러발생");
    }
}
