package com.hjj.util;

import java.io.Serializable;

public class RespResult<T> implements Serializable {
    private T data;
    private Integer code;
    private String message;

    public RespResult() {}

    public RespResult(T data, RespCode resultCode) {
        this.data = data;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public RespResult(RespCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public static RespResult ok(){
        return new RespResult(null,RespCode.SUCCESS);
    }

    public static <T> RespResult<T> ok(T data){
        return new RespResult<T>(data,RespCode.SUCCESS);
    }

    public static RespResult error(){
        return new RespResult(null,RespCode.ERROR);
    }

    public static RespResult error(String message){
        return secByError(RespCode.ERROR.getCode(),message);
    }

    public static RespResult error(RespCode resultCode){
        return new RespResult(resultCode);
    }

    public static RespResult secByError(Integer code,String message){
        RespResult err = new RespResult();
        err.setCode(code);
        err.setMessage(message);
        return err;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
