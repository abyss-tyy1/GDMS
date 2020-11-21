package com.GDMS.controller;

import com.GDMS.dto.StudentAccountReqDto;
import com.GDMS.dto.SubjectReqDto;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "课题管理接口")
@RestController
@RequestMapping(value = "/subject",method = RequestMethod.POST)
public class SubjectController {

    @Resource
    ISubjectService subjectService;

    @ApiOperation(value = "学生登陆")
    @RequestMapping(value = "/getSubject",method = RequestMethod.POST)
    RestResponse getSubject(@RequestBody SubjectReqDto dto) {
        return new RestResponse(subjectService.getAllSubject(dto));
    }

}
