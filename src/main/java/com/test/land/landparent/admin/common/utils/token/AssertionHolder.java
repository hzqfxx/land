package com.test.land.landparent.admin.common.utils.token;

import com.test.land.landparent.admin.entity.AuthModel;

/**
 * 线程变量-获取登录用户
 */
public class AssertionHolder {
    private static final ThreadLocal<AuthModel> THREAD_LOCAL = new ThreadLocal<AuthModel>();

    public static AuthModel getAuthModel() {
        return THREAD_LOCAL.get();
    }

    public static void setAuthModel(AuthModel authModel) {
        THREAD_LOCAL.set(authModel);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
