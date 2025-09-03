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
    USER_EXISTED(HttpStatus.BAD_REQUEST.value(), "Tài khoản người dùng đã tồn tại"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(), "Không đủ thẩm quyền"),
    PASSWORD_CONFIRM_NOT_MATCH(400, "Mật khẩu xác nhận không khớp"),
    TOKEN_SIGNING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Không thể tạo mã xác thực"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), "Token không hợp lệ"),
    EMPLOYEE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "Tài khoản nhân viễn đã tồn tại"),
    USER_ASSIGNMENT_CANNOT_BE_CHANGED(HttpStatus.BAD_REQUEST.value(), "Không thể đổi user liên kết"),
    UNSUPPORTED_ADDRESS_REGION(HttpStatus.BAD_REQUEST.value(),"Khu vực không được hỗ trợ"),

    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy đơn hàng");


    private final int code;
    private final String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
