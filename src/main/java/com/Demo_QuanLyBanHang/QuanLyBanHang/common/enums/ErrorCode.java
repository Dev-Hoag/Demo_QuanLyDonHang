package com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "Yêu cầu không hợp lệ"),
    HUBADDRESS_EXISTED(HttpStatus.BAD_REQUEST.value(), "Địa chỉ trạm giao nhận đã tồn tại"),
    INVALID_TRANSIT_ARRIVED_OR_DEPARTURED(HttpStatus.BAD_REQUEST.value(), "Thời gian không được để trống"),
    INVALID_DEPARTURED(HttpStatus.BAD_REQUEST.value(), "Thời gian rời trạm phải sau hoặc bằng thời gian đến trạm"),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST.value(), "Thiếu trường bắt buộc"),
    INVALID_ID(HttpStatus.BAD_REQUEST.value(), "ID không hợp lệ"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Không có quyền truy cập"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Không đủ quyền thực hiện chức năng này"),
    DUPLICATE_RESOURCE(HttpStatus.BAD_REQUEST.value(), "Tài nguyên đã tồn tại"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy tài nguyên"),
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy trạm giao nhận"),
    CONFLICT(HttpStatus.CONFLICT.value(), "Xung đột dữ liệu"),
    VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Dữ liệu không hợp lệ"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lỗi truy vấn cơ sở dữ liệu"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy người dùng"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy đơn hàng");


    private final int code;
    private final String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
