package com.cp.base.handler;

import com.cp.base.enumeration.CommonError;
import com.cp.base.exception.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * handle system error
     * @param e system exception
     * @return restErrorResponse
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e){
        //record error
        log.info("!!!system error : {},{}",e.getMessage(),e);
        return new RestErrorResponse(CommonError.UNKNOWN_ERROR.getErrorMessage());
    }
}
