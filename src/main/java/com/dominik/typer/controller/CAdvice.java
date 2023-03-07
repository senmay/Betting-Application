package com.dominik.typer.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CAdvice extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({FileNotFound.class})
//    public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .build();
//        return handleExceptionInternal(ex, errorResponse, null, HttpStatus.NOT_FOUND, request);
//    }
//
//    @ExceptionHandler({BadUserParamException.class})
//    public ResponseEntity<Object> handleWrongUserParamsException(Exception ex, WebRequest request){
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .build();
//        return handleExceptionInternal(ex, errorResponse, null, HttpStatus.BAD_REQUEST, request);
//    }
//    //MethodArgumentNotValidException
//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldError = result.getFieldErrors();
//        List<String> errors = new ArrayList<>();
//
//        for(FieldError error : fieldError){
//            errors.add(error.getDefaultMessage());
//        }
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .fieldErrors(errors)
//                .build();
//
//        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(
//            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .build();
//
//        return handleExceptionInternal(ex, errorResponse, headers, status, request);
//    }
//
//    @ExceptionHandler({NullPointerException.class, NumberFormatException.class, IllegalArgumentException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleInvalidNumberException(Exception ex, WebRequest request) {
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .build();
//
//        return handleExceptionInternal(ex, errorResponse, null, HttpStatus.BAD_REQUEST, request);
//    }

}
