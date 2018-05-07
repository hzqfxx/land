package com.test.land.landparent.admin.common.utils;



import com.alibaba.fastjson.JSON;
import com.test.land.landparent.admin.common.Enum.SystemEnum;
import com.test.land.landparent.admin.common.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回结果工具
 */

@Slf4j
public class ResultUtils {

    /**
     *处理返回结果
     */
    public static <T>Result<T> ok(T data,SystemEnum systemEnum){
        Result<T> result = new Result<>();
        if (SystemEnum.PERRATING_SUCCESS.getCode().equals(systemEnum.getCode())){
            //操作成功
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
        }
        result.setData(data);
        result.setCode(systemEnum.getCode());
        result.setMessage(systemEnum.getMessage());
        log.info("return info ===>"+ JSON.toJSONString(result));
        return result;
    }

    /**
     *处理返回结果
     */
    public static <T>Result<T> ok(T data,String code,String message){
        Result<T> result = new Result<>();
        if (SystemEnum.PERRATING_SUCCESS.getCode().equals(code)){
            //操作成功
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
        }
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        log.info("return info ===>"+ JSON.toJSONString(result));
        return result;
    }
}

