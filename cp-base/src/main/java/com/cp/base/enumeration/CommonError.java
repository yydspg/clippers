package com.cp.base.enumeration;

/**
 * @description 通用描述信息
 * @author paul
 */
public enum CommonError {
    UNKNOWN_ERROR("执行过程异常,请重试"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");


    private String errorMessage;


    /**
     * 每个枚举常量在创建时都会调用构造器，并将一个字符串参数传递给构造器。构造器将这个字符串参数存储在 errMessage 属性中，这样每个枚举常量都有了一个与之关联的错误消息。
     * @param errorMessage
     */
    private CommonError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
}
