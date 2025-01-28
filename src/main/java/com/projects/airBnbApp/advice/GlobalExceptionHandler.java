package com.projects.airBnbApp.advice;

import com.projects.airBnbApp.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
////        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
//
//        return  new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(ResourceNotFoundException.class)
    /*-----------METHOD 2------------*/
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
//        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return  buildErrorResponseEntity(apiError);
    }


    /*Method 2 till here*/

        /* --------------------METHOD 1-----------------------
        public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
//        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
         ApiError apiError = ApiError.builder()
                 .status(HttpStatus.NOT_FOUND)
                 .message(exception.getMessage())
                 .build();

        return  new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    } */


    /* ----------------------Method 1 for section 2 till here --------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return  new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

     */

    /* ------------------------------Method 2------------------------*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return  buildErrorResponseEntity(apiError);
    }

    /* ------------------------Method 1 for this section --------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subErrors(errors )
                .build();

        return  new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }
     */

    /*--------------------------------------Method 2 for this section */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subErrors(errors )
                .build();

        return  buildErrorResponseEntity(apiError);

    }

    //Internal methods
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
