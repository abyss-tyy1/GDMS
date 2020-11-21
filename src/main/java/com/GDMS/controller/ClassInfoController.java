package com.GDMS.controller;

import com.GDMS.dto.ClassInfoReqDto;
import com.GDMS.entity.ClassInfoEntity;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"教室信息管理接口"})
@RestController
@RequestMapping(value = "/classInfo")
public class ClassInfoController {

    @Autowired
    ClassInfoService classInfoService;


    @ApiOperation(value = "测试pageHelper，分页查询教室信息")
    @RequestMapping(value = "/queryClassInfo",method = RequestMethod.POST)
    RestResponse queryClassInfo(@RequestBody ClassInfoReqDto dto) {
        return classInfoService.queryClassInfo(dto);
    }


    /**
     * 测试的时候不需要传timestamp就行了；
     * @param dto
     * @return
     */
    @ApiOperation(value = "测试批量添加")
    @RequestMapping(value = "/addClassInfo",method = RequestMethod.POST)
    RestResponse addClassInfo(@RequestBody List<ClassInfoEntity> dto) {
        return new RestResponse(classInfoService.insert(dto));
    }

    @ApiOperation(value = "导出教室信息")
    @RequestMapping(value = "/exportClassInfo",method = RequestMethod.POST)
    RestResponse exportClassInfo(@RequestBody ClassInfoReqDto dto) {
        return classInfoService.exportClassInfo(dto);
    }

}
