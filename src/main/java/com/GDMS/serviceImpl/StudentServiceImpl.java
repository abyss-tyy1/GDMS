package com.GDMS.serviceImpl;

import com.GDMS.dto.StudentAccountReqDto;
import com.GDMS.entity.StudentAccountEntity;
import com.GDMS.mapper.StudentMapper;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.IStudentService;
import com.GDMS.utils.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    StudentMapper studentMapper;

    @Override
    public Map<String,String> login(StudentAccountReqDto dto) {

        if (StringUtils.isEmpty(dto.getUserName()) || StringUtils.isEmpty(dto.getPassword())){
            throw  new BusinessException("账号和密码不能为空！");
        }
        Map<String,String> map = new HashMap<>();
        StudentAccountEntity student = studentMapper.getStudent(dto);
        if (ObjectUtils.isEmpty(student)){
         throw new BusinessException("不存在该用户！");
        }else {
            map.put("loginStatus","SUCCESS");
            return map;
        }
    }
}
