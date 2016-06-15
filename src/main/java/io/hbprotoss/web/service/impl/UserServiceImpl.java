package io.hbprotoss.web.service.impl;

import io.hbprotoss.web.mapper.UserMapper;
import io.hbprotoss.web.model.UserModel;
import io.hbprotoss.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hbprotoss on 9/27/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public UserModel getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }
}
