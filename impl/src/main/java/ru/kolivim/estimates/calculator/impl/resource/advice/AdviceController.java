package ru.kolivim.estimates.calculator.impl.resource.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kolivim.estimates.calculator.impl.exception.AccountException;
import ru.kolivim.estimates.calculator.impl.exception.AuthException;
import ru.kolivim.estimates.calculator.impl.exception.NotFoundException;
import ru.kolivim.estimates.calculator.impl.exception.TokenException;

@Slf4j
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException(NotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleException(AuthException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<String> handleException(AccountException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<String> handleException(TokenException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(401).body(e.getMessage());
    }

}
