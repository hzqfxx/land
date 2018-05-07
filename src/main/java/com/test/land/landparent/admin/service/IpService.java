package com.test.land.landparent.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.test.land.landparent.admin.entity.Ip;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
public interface IpService extends IService<Ip> {

    List<Ip> getAvailableIP();

}
