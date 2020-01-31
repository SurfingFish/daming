package com.thebund1st.daming.adapter.spring.web.advice;

import com.thebund1st.daming.application.domain.exceptions.MobileIsNotUnderVerificationException;
import com.thebund1st.daming.application.domain.exceptions.SmsVerificationCodeMismatchException;
import com.thebund1st.daming.security.ratelimiting.TooManyRequestsException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public abstract class DefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TooManyRequestsException.class})
    protected ResponseEntity<Object> handleTooManyRequests(
            TooManyRequestsException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        errorAttributes.put("error", "1001");
        errorAttributes.put("message", ex.getMessage());
        return handleExceptionInternal(ex, errorAttributes,
                new HttpHeaders(), HttpStatus.TOO_MANY_REQUESTS, request);
    }

    @ExceptionHandler(value = {SmsVerificationCodeMismatchException.class})
    protected ResponseEntity<Object> handleCodeMismatch(
            SmsVerificationCodeMismatchException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        errorAttributes.put("error", "1101");
        errorAttributes.put("message", ex.getMessage());
        return handleExceptionInternal(ex, errorAttributes,
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler(value = {MobileIsNotUnderVerificationException.class})
    protected ResponseEntity<Object> handleNotUnderVerification(
            MobileIsNotUnderVerificationException ex, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        errorAttributes.put("error", "1102");
        errorAttributes.put("message", ex.getMessage());
        return handleExceptionInternal(ex, errorAttributes,
                new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    private Map<String, Object> getErrorAttributes(WebRequest request) {
        DefaultErrorAttributes errorAttributes = new DefaultErrorAttributes(false);
        return errorAttributes.getErrorAttributes(request, false);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return handleExceptionInternal(ex, errorAttributes, headers, status, request);
    }

}
