package com.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * @author comlink
 *
 */
@ControllerAdvice 
public class ExceptionAdvice {
	
	@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleIOException(NullPointerException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>("exception",HttpStatus.NOT_FOUND);
        return response;
        
    }
}
