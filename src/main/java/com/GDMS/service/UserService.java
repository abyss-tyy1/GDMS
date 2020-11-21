package com.GDMS.service;

import com.GDMS.dto.UserRequestDto;
import com.GDMS.entity.UserEntity;

import java.util.List;

public interface UserService {

    /**
     * 查询user信息
     * @param dto
     * @return
     */
    List<UserEntity> findAllUser(UserRequestDto dto);
}
