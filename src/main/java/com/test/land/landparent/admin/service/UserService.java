package com.test.land.landparent.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.test.land.landparent.admin.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
public interface UserService extends IService<User> {

    /**
     * 缓存击穿的解决方案
     * @return
     */
    List<User> getUser();

    /**
     * 注册
     * @param user
     * @return
     */
    Boolean register(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    Map login(User user);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAll();

    void add();


}
