package com.GDMS.serviceImpl;

import com.GDMS.dto.UserRequestDto;
import com.GDMS.entity.UserEntity;
import com.GDMS.mapper.UserMapper;
import com.GDMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserService userService;

    @Resource
    UserMapper userMapper;


    @Override
    public List<UserEntity> findAllUser(UserRequestDto dto) {
        return userMapper.findAllUser(dto);
    }

    

}
