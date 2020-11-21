package com.GDMS.mapper;

import com.GDMS.dto.UserRequestDto;
import com.GDMS.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {

    //逗号与“+”号都是可以的 记得大括号就行了；有@param才能用dto.id的方式
    @Select({"<script>",
            "SELECT a.id,a.userName,a.pwd passWord,a.cx authority,a.addTime FROM alluser a",
            "WHERE 1=1",
            "<when test='dto.userId!=0'> AND a.id = #{dto.userId} </when>",
            "<when test='dto.userName!=null'> AND a.userName like CONCAT('%',#{dto.userName},'%')  </when>",
            "</script>"}
    )
    List<UserEntity> findAllUser(@Param("dto") UserRequestDto dto);
}
