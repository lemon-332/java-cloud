package com.hjj.util;

public enum RespCode {
    SUCCESS(20000, "请求成功"),
    ERROR(50000, "请求失败"),
    SYSTEM_ERROR(50001, "系统异常");

    private Integer code;
    private String message;

    RespCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    RespCode() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
