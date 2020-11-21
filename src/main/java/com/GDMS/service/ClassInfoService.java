package com.GDMS.service;

import com.GDMS.dto.ClassInfoReqDto;
import com.GDMS.entity.ClassInfoEntity;
import com.GDMS.rest.RestResponse;

import java.util.List;

public interface ClassInfoService {

    /**
     * 查询教室信息
     * @param dto
     * @return
     */
    RestResponse queryClassInfo(ClassInfoReqDto dto);

    int insert(List<ClassInfoEntity> entities);

    /**
     * 导出教室信息
     * @param dto
     * @return
     */
    RestResponse exportClassInfo(ClassInfoReqDto dto);
}
