package com.test.land.landparent.admin.common;

import lombok.Data;

/**
 * 通用结果类
 */
@Data
public class Result<T> {

    /**
     * 调用接口是否成功
     */
    public Boolean success=true;

    /**
     * 返回码
     */
    public String code;

    /**
     * 返回结果
     */
    public T data;

    /**
     * 返回信息
     */
    public String message;

    public Boolean isSuccess(){
        return success;
    }

}
