package com.jielim36.devgram.handler;

import com.jielim36.devgram.common.Response.ResultCode;
import com.jielim36.devgram.common.Response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse<String> exception(Exception e) {
        return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR,e.getMessage());
    }


}