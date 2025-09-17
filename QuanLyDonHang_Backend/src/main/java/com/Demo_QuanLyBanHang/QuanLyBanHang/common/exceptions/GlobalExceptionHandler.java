package com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        var response = ApiResponse.builder()
                .statusCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .error("AppException")
                .build();
        return ResponseEntity.status(errorCode.getCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        var response = ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .error("ValidationException")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleJsonParseException(HttpMessageNotReadableException ex) {
        var response = ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Dữ liệu không hợp lệ vui lòng kiểm tra lại định dạng")
                .error("HttpException")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        var response = ApiResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .error("UnexpectedException")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
