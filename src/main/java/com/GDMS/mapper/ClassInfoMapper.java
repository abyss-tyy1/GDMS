package com.GDMS.mapper;

import com.GDMS.dto.ClassInfoReqDto;
import com.GDMS.entity.ClassInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassInfoMapper {


    @Select({"<script>",
                "select a.* from classInfo a",
                "where 1=1",
                    "<when test='dto.className!=null'> AND a.className like CONCAT('%',#{dto.className},'%') </when>",
            "</script>"
    })
    List<ClassInfoEntity> findAllClassInfo(@Param("dto") ClassInfoReqDto dto);

    @Insert({
            "<script>",
                "insert into classInfo (id,className,allSeatCount,restSeatCount,classPhoto,briefIntroduction) values",
                    "<foreach collection='list' item='entity' index='index' separator=','>",
                        "(#{entity.id}, #{entity.className}, #{entity.allSeatCount}, #{entity.restSeatCount}, #{entity.classPhoto}, #{entity.briefIntroduction} )",
                    "</foreach>",
            "</script>"
    })
    int insert(@Param("list") List<ClassInfoEntity> list);

}
