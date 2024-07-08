package com.example.auth_app_new.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String message;

    private boolean success;

    private int code;

    private Object Data;
    private String token;

    public ApiResponse(String message, boolean success, int code, String token) {
        this.message = message;
        this.success = success;
        this.code = code;
        this.token = token;
    }

    public ApiResponse(String message, boolean success, int code, Object data) {
        this.message = message;
        this.success = success;
        this.code = code;
        Data = data;
    }
}
