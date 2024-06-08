package com.jielim36.devgram.common;

import com.jielim36.devgram.enums.ResultCode;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {

    private String code;
    private String message;
    private T data;
    public ResultResponse(ResultCode errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }
    public  static ResultResponse success(){
        ResultResponse resultResponse = new ResultResponse(ResultCode.SUCCESS);
        return resultResponse;
    }
    public  static<T> ResultResponse success(T t){
        ResultResponse resultResponse = success();
        resultResponse.setData(t);
        return resultResponse;
    }
    public  static ResultResponse failure(ResultCode resultCode){
        ResultResponse resultResponse = new ResultResponse(resultCode);
        return resultResponse;
    }
    public  static<T> ResultResponse failure(ResultCode resultCode, T t){
        ResultResponse resultResponse = failure(resultCode);
        resultResponse.setData(t);
        return resultResponse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
