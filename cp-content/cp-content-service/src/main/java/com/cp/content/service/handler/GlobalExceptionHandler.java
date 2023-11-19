package com.cp.content.service.handler;

import com.cp.base.exception.RestErrorResponse;
import com.cp.content.service.exception.CpException;
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
     * handle custom exception
     * @param e custom exception
     * @return The front-end convention returns an exception message
     */
    @ResponseBody
    @ExceptionHandler(CpException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(CpException e){
        //record exception
        log.info("!!!system exception :{},{}",e.getErrMessage(),e);
        String errMessage = e.getErrMessage();
        return new RestErrorResponse(errMessage);
    }
}
