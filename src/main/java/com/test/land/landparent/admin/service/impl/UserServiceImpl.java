package com.test.land.landparent.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.test.land.landparent.admin.common.constants.Constants;
import com.test.land.landparent.admin.common.utils.AssertUtils;
import com.test.land.landparent.admin.common.utils.DateUtils;
import com.test.land.landparent.admin.common.utils.token.TokenManager;
import com.test.land.landparent.admin.entity.AuthModel;
import com.test.land.landparent.admin.entity.User;
import com.test.land.landparent.admin.entity.request.UserDTO;
import com.test.land.landparent.admin.mapper.UserDao;
import com.test.land.landparent.admin.service.CacheLoader;
import com.test.land.landparent.admin.service.RedisService;
import com.test.land.landparent.admin.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private CacheTemplateService cacheTemplateService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenManager tokenManager;

    /**
     * 缓存击穿的解决方案
     * @return
     */
    @Override
    public List<User> getUser() {
        String key="cache key";
        List<User> data = cacheTemplateService.findData(key, 60, new TypeReference<List<User>>() {
        }, new CacheLoader<List<User>>() {
            @Override
            public List<User> load() {
                EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
                return userDao.selectList(userEntityWrapper);
            }
        });

        return data;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Boolean register(User user) {
        Date now = DateUtils.now();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("username", user.getName()).or().eq("telephone", user.getTelephone());
        List<User> users = userDao.selectList(userEntityWrapper);
        try {
            if (CollectionUtils.isEmpty(users)){
                user.setCreateTime(now);
                user.setLastloginTime(now);
                Integer insert = userDao.insert(user);
                int i=1/0;
                return insert >0?Boolean.TRUE:Boolean.FALSE;

            }
        }catch (Exception e){
            System.out.println("事务不回滚");
        }

        return Boolean.FALSE;
    }

    /**
     * 登陆
     * @param user
     * @return
     */
    @Override
    public HashMap<String,Object> login(User user) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        User tUser = userDao.selectOne(user);
        AssertUtils.notNull(tUser,"用户密码错误");
        //创建token存入redis
        UserDTO userDTO = new UserDTO();
        userDTO.setCode(tUser.getUsername());
        userDTO.setName(tUser.getName());
        userDTO.setId(tUser.getId());
        userDTO.setTelephone(tUser.getTelephone());
        AuthModel authModel = tokenManager.createAuthModel(Constants.PLATFORM, userDTO);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put(Constants.AUTHORIZATION,authModel.getToken());
        map.put("id",userDTO.getId());
        map.put("username",userDTO.getCode());
        map.put("name",userDTO.getName());
        return map;

    }

    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @Override
    public void add() {
        long now = System.currentTimeMillis();
        userDao.insertUser();
        long t1 = System.currentTimeMillis();
        //userDao.insertUser2();
        long t2 = System.currentTimeMillis();
        System.out.println("2次sql可以执行否"+(t1-now));
        System.out.println("加事务"+(t2-t1));
    }


}
