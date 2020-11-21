package com.GDMS.service;

import com.GDMS.dto.StudentAccountReqDto;
import com.GDMS.entity.StudentAccountEntity;
import com.GDMS.rest.RestResponse;

import java.util.List;
import java.util.Map;

public interface IStudentService {

    Map<String,String> login(StudentAccountReqDto dto);

}
