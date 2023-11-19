package com.cp.content.service.exception;

import com.cp.base.enumeration.CommonError;

public class CpException extends RuntimeException{
    private String errMessage;
    public CpException(){

    }
    public CpException(String message){
        super(message);
        this.errMessage = message;
    }
    public String getErrMessage(){
        return this.errMessage;
    }
    public void setErrMessage(String message){
        this.errMessage = message;
    }
    //工厂方法
    public static void cast(String message){
        throw new CpException(message);
    }

    public static void cast(CommonError commonError){
        throw new CpException(commonError.getErrorMessage());
    }
}
