package com.test.land.landparent.admin.common.utils.token;

import com.test.land.landparent.admin.entity.AuthModel;
import com.test.land.landparent.admin.entity.request.UserDTO;

/**
 *token工具类
 */
public interface TokenManager {


    /**
     * 创建一个token关联上指定用户
     * @param platform
     * @param user
     * @return
     */
    AuthModel createAuthModel(String platform, UserDTO user);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkAuthModel(AuthModel model);

    /**
     * 通过jwt验证信息是否合法，并返回用户tokenmodel
     * @param token
     * @return
     */
    AuthModel getAuthModel(String token);

    /**
     * 清除token
     * @param model 登录用户的信息
     */
    void deleteAuthModel(AuthModel model);
}
