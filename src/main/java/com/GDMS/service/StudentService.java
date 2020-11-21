package com.GDMS.service;

import com.GDMS.dto.StudentResDto;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生信息；
     * @param teacherId
     * @return
     */
    List<StudentResDto> findAllStudent(Long teacherId);

    /**
     * 测试异步执行
     * @return
     */
     long testPool();

    /**
     * 查询异步的结果
     * @return
     */
     long queryPool(Long threadId);
}
