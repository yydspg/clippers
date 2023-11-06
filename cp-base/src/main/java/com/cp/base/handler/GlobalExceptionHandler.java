package com.cp.base.handler;

import com.cp.base.enumeration.CommonError;
import com.cp.base.exception.CpException;
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
     * handle custom exception
     * @param e custom exception
     * @return The front-end convention returns an exception message
     */
    @ResponseBody
    @ExceptionHandler(CpException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(CpException e){
        //record exception
        log.info("system exception :{},{}",e.getErrMessage(),e);
        String errMessage = e.getErrMessage();
        RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
        return restErrorResponse;
    }

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
        log.info("system error : {},{}",e.getMessage(),e);
        RestErrorResponse restErrorResponse = new RestErrorResponse(CommonError.UNKNOWN_ERROR.getErrorMessage());
        return restErrorResponse;
    }
}
