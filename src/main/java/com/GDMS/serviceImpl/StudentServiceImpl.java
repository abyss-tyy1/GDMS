package com.GDMS.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.GDMS.dto.AsynchronousHandler;
import com.GDMS.dto.StudentResDto;
import com.GDMS.entity.ClassInfoEntity;
import com.GDMS.service.StudentService;
import com.GDMS.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ExecutorService executorService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<StudentResDto> findAllStudent(Long teacherId) {
        List<StudentResDto> studentResDtos = new ArrayList<>();
        StudentResDto dto = new StudentResDto();
        if (teacherId==1L){
            for (int i = 0; i < 5; i++) {
                dto.setAge(21);
                dto.setHobby("lanqiu");
                dto.setStatus(1);
                dto.setStudentName("abyss");
                studentResDtos.add(dto);
            }
        }
        return studentResDtos;
    }

    @Override
    public long testPool() {
        long threadId = System.currentTimeMillis();
        executorService.submit(new AsynchronousHandler(threadId,stringRedisTemplate));
        return threadId;
    }


    /**
     * 对于实redis还可以在实现类中加一个无返回类型的initial类，初始化一下缓存信息，以此来减少查询数据库的次数；
     * @param threadId
     * @return
     */
    @Override
    public long queryPool(Long threadId) {
        Long type = 0L;
        try {
            String json = stringRedisTemplate.opsForValue().get(threadId+"");
            ClassInfoEntity classInfoEntity = JSON.parseObject(json, ClassInfoEntity.class);
            assert classInfoEntity != null;
            type=classInfoEntity.getId();

        }catch (Exception e){
            throw new BusinessException("查询失败"+e.toString());
        }
        return type;
    }


}
