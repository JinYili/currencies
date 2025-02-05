package com.jinyili.currencies;

import jdk.jshell.spi.ExecutionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> runtimeError(Exception ex) {
        logger.error("An ERROR occurred in reason of {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> httpError(HttpClientErrorException ex){
        int num = ex.getStatusCode().value();
        logger.error("An ERROR occurred in unsupported Currency");
        return ResponseEntity.status(num).body("Currency not supported");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> parameterError(HttpMessageNotReadableException ex){
        logger.error("An ERROR occurred in post body");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Missing post body");
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> noResourceError(NoResourceFoundException ex){
        logger.error("An ERROR occurred in calling path");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error on calling method or path");
    }

    @ExceptionHandler({ExecutionControl.NotImplementedException.class})
    public ResponseEntity<Object> notImplementError(ExecutionControl.NotImplementedException ex){
        logger.error("Call function not implemented yet");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error on calling function not implemented");
    }
}
