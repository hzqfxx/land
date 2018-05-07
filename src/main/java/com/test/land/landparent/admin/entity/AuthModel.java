package com.test.land.landparent.admin.entity;

import com.test.land.landparent.admin.entity.request.UserDTO;

/**
 * 认证用户实体
 */
public class AuthModel {
    private static final long serialVersionUID = 8502915098113578271L;
    /**
     * 用户
     */
    private UserDTO user;
    /**
     * 生成该token的平台
     */
    private String platform;

    //jwt生成的token
    private String token;

    public AuthModel(UserDTO user, String token, String platform) {
        this.token = token;
        this.user = user;
        this.platform = platform;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "AuthModel [user=" + user + ", platform=" + platform
                + ", token=" + token + "]";
    }
}
