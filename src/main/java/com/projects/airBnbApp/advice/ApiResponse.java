package com.projects.airBnbApp.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data;

//    @Pattern(regexp = "hh-mm-ss dd-mm-yyyy")
    @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timestamp;

    private ApiError apiError;

    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }


}
