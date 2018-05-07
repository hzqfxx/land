package com.test.land.landparent.admin.service.impl;

import com.test.land.landparent.admin.entity.Customer;
import com.test.land.landparent.admin.mapper.CustomerDao;
import com.test.land.landparent.admin.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {

}
