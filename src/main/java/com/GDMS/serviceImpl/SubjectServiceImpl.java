package com.GDMS.serviceImpl;

import com.GDMS.dto.SubjectReqDto;
import com.GDMS.entity.SubjectEntity;
import com.GDMS.mapper.SubjectMapper;
import com.GDMS.service.ISubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SubjectServiceImpl implements ISubjectService {

    @Resource
    SubjectMapper subjectMapper;
    @Override
    public List<SubjectEntity> getAllSubject(SubjectReqDto dto) {
        return subjectMapper.getAllSubject(dto);
    }
}
