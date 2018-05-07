package com.test.land.landparent.admin.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.land.landparent.admin.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    void insertUser();

    void insertUser2();

}
