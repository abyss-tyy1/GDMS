package com.GDMS.mapper;

import com.GDMS.dto.SubjectReqDto;
import com.GDMS.entity.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Select({
            "<script>",
            "SELECT a.* FROM all_subject a",
            "WHERE 1=1",
            "<when test='dto.id!=0'> and a.id=#{dto.id} </when>",
            "and a.dr=0",
            "</script>"
    })
    List<SubjectEntity> getAllSubject(@Param("dto")SubjectReqDto dto);
}
