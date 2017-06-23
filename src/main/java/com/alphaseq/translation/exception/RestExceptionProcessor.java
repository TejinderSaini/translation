package com.alphaseq.translation.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alphaseq.translation.entity.Status;

@ControllerAdvice
public class RestExceptionProcessor {
     
    @ExceptionHandler(AlphaseqIllegalArgException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Status argumentNotFound(HttpServletRequest req, AlphaseqIllegalArgException ex) {    	
    	return ex.getErrorStatus();
    }
 
}
