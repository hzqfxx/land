package com.test.land.landparent.admin.web;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.land.landparent.admin.common.Enum.SystemEnum;
import com.test.land.landparent.admin.common.Result;
import com.test.land.landparent.admin.common.utils.AssertUtils;
import com.test.land.landparent.admin.common.utils.ResultUtils;
import com.test.land.landparent.admin.entity.User;
import com.test.land.landparent.admin.service.UserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody User user){
        AssertUtils.notNull(user.getName(),"姓名不能为空");
        AssertUtils.notNull(user.getUsername(),"用户名不能为空");
        AssertUtils.notNull(user.getPassword(),"密码不能为空");
        Boolean register = userService.register(user);
        return ResultUtils.ok(register,SystemEnum.PERRATING_SUCCESS);
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        AssertUtils.notNull(user.getUsername(),"用户名不能为空");
        AssertUtils.notNull(user.getPassword(),"密码不能为空");
        Map login = userService.login(user);
        return ResultUtils.ok(login,SystemEnum.PERRATING_SUCCESS);
    }

    @GetMapping("/getAll")
    public Result getAll(){
        return ResultUtils.ok(userService.getAll(),SystemEnum.PERRATING_SUCCESS);
    }
    @GetMapping("/add" )
    public Result add(){
        userService.add();
        return null;
    }






}

