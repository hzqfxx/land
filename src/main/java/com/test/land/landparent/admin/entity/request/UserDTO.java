package com.test.land.landparent.admin.entity.request;

import com.test.land.landparent.admin.entity.User;

import lombok.Data;

/**
 * 请求用户接收实体
 */
@Data
public class UserDTO extends User{

    private static final long serialVersionUID = -4752830652748335103L;

    private String code;

    private String newPassword;

    private String oldPassword;

    /**工号 5或8位数字**/
    private String oaCode;

    public String getOaCode() {
        return oaCode;
    }

    public void setOaCode(String oaCode) {
        this.oaCode = oaCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
