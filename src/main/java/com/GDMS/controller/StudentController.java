package com.GDMS.controller;

import com.GDMS.dto.StudentAccountReqDto;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"学生管理接口"})
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Resource
    IStudentService studentService;


    @ApiOperation(value = "学生登陆")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    RestResponse userLogin(@RequestBody StudentAccountReqDto dto) {
        return new RestResponse(studentService.login(dto));
    }



}
