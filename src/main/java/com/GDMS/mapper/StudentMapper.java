package com.GDMS.mapper;

import com.GDMS.dto.StudentAccountReqDto;
import com.GDMS.entity.StudentAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StudentMapper {



    @Select({
            "<script>",
            "SELECT a.* FROM student_account a",
            "WHERE a.userName=#{dto.userName} and a.password=#{dto.password}",
            "</script>"
    })
    StudentAccountEntity getStudent(@Param("dto")StudentAccountReqDto dto);
}
