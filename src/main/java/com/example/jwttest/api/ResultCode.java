package com.example.jwttest.api;

/**
 * 常用API返回对象
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Success"),
    FAILED(500, "Fail"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "token已失效"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
