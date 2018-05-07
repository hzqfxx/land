package com.test.land.landparent.admin.common.Enum;

/**
 * 操作码枚举
 */
public enum SystemEnum {

    PERRATING_SUCCESS("0000","操作成功"),
    OPERATION_FAILURE("0001","操作失败");

    private String code;

    private String message;


    private SystemEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
