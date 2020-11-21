package com.GDMS.service;

import com.GDMS.dto.SubjectReqDto;
import com.GDMS.entity.SubjectEntity;

import java.util.List;

public interface ISubjectService {

    List<SubjectEntity> getAllSubject(SubjectReqDto dto);
}
