package com.test.land.landparent.admin.config;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.land.landparent.admin.common.Enum.SystemEnum;
import com.test.land.landparent.admin.common.Result;


/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Result exceptionHandler(Exception ex){
        logger.error("发生异常",ex);
        Result result =new Result();
        result.setCode(SystemEnum.OPERATION_FAILURE.getCode());
        result.setSuccess(false);
        result.setMessage(ex.getMessage());
        return result;
    }

    /**
     * 用来处理bean validation异常 针对方法内参数校验
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result resolveConstraintViolationException(ConstraintViolationException ex){
        Result result = new Result();
        result.setCode(SystemEnum.OPERATION_FAILURE.getCode());
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            result.setMessage(errorMessage);
            logger.info("参数校验不通过:{}",errorMessage);
            return result;
        }
        result.setMessage(ex.getMessage());
        result.setSuccess(false);
        logger.info("参数校验不通过:{}",ex.getMessage());
        return result;
    }

    /**
     * 用来处理bean validation异常 针对单独类的校验
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Result result = new Result();
        result.setCode(SystemEnum.OPERATION_FAILURE.getCode());
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            result.setMessage(errorMessage);
            logger.info("参数校验不通过:{}",errorMessage);
            return result;
        }
        result.setMessage(ex.getMessage());
        result.setSuccess(false);
        logger.info("参数校验不通过:{}",ex.getMessage());
        return result;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result resolveBeanPropertyBindingResult(BindException ex){
        Result result = new Result();
        result.setCode(SystemEnum.OPERATION_FAILURE.getCode());
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            result.setMessage(errorMessage);
            logger.info("参数校验不通过:{}",errorMessage);
            return result;
        }
        result.setMessage(ex.getMessage());
        result.setSuccess(false);
        logger.info("参数校验不通过:{}",ex.getMessage());
        return result;
    }
}
