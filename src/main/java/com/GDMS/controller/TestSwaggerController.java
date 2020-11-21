package com.GDMS.controller;

import com.alibaba.fastjson.JSON;
import com.GDMS.dto.StudentResDto;
import com.GDMS.dto.UserRequestDto;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.StudentService;
import com.GDMS.service.UserService;
import com.GDMS.utils.SnowFlake;
import com.GDMS.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@Api(tags = {"swagger测试接口"})
@RestController
@RequestMapping(value = "/student")
public class TestSwaggerController {

    private static final Logger logger = LoggerFactory.getLogger(TestSwaggerController.class.getName());
    @Autowired
    private StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "测试swagger")
    @RequestMapping(value = "/findAllStudent",method = RequestMethod.POST)
    public RestResponse findAll(@RequestParam(value = "teacherId") Long teacherId){
         return new RestResponse(studentService.findAllStudent(teacherId));
    }

    @ApiOperation(value = "测试")
    @RequestMapping(value = "/sayHello",method = RequestMethod.POST)
    public RestResponse sayHello(@RequestBody StudentResDto dto){
        long id = SnowFlake.nextId();
        String words = dto.getStudentName() + "," + "你好啊！我知道你的爱好是" + dto.getHobby() +
                "，今年是" + dto.getAge() + "岁呢！"+"生成的id是："+id;
        return new RestResponse(words);
    }


    @ApiOperation(value = "测试mybatis的注解写法")
    @RequestMapping(value = "/testMybatis",method = RequestMethod.POST)
    public RestResponse testMybatis(@RequestBody UserRequestDto dto){

        return new RestResponse(userService.findAllUser(dto));
    }

    @ApiOperation(value = "测试AsyPool")
    @RequestMapping(value = "/pool",method = RequestMethod.POST)
    public RestResponse pool(@RequestBody StudentResDto dto){
        long testPool = studentService.testPool();
        return new RestResponse(testPool);
    }
   /*   可以使其一开始就运行这个初始化方法；
        public void initialRedis(){
        stringRedisTemplate.opsForValue().set("abyss","he always can putUp for jump shut in main time!",30,TimeUnit.SECONDS);
    }*/

    @ApiOperation(value = "查询AsyPool")
    @RequestMapping(value = "/queryAsyn",method = RequestMethod.POST)
    public RestResponse queryAsyn( @RequestParam(name = "threadId") Long threadId){
        long result = studentService.queryPool(threadId);
        return new RestResponse(result);
    }

    @ApiOperation(value = "测试自定义异常类处理器")
    @RequestMapping(value = "/testThrowException",method = RequestMethod.POST)
    public RestResponse testThrowException(@RequestBody StudentResDto dto){
        if (dto.getStudentName().equals("abyss")) {
            throw new BusinessException("测试异常抛出！抛出者：abyss");
        }
        return RestResponse.SUCCESS;
    }

    @ApiOperation(value = "测试redis")
    @RequestMapping(value = "/testRedis",method = RequestMethod.POST)
    public RestResponse testRedis(@RequestBody StudentResDto dto){
        StudentResDto studentResDto = new StudentResDto();
        try {
            dto.setAge(dto.getAge()+1);
            //set进一个string json串 时间是20秒；
            stringRedisTemplate.opsForValue().set(dto.getStudentName(), JSON.toJSONString(dto),20, TimeUnit.SECONDS);
            String jsonString = stringRedisTemplate.opsForValue().get(dto.getStudentName());
            //string 转成 java对象；
            studentResDto = JSON.parseObject(jsonString, StudentResDto.class);
        }catch (Exception e){
            throw new BusinessException(e.toString());
        }

        return new RestResponse(studentResDto);
    }


    @ApiOperation(value = "测试log4j2")
    @RequestMapping(value = "/testLog4j2",method = RequestMethod.POST)
    public RestResponse testLog4j2(@RequestBody StudentResDto dto){
        logger.info("测试log4j2的使用入参为：{}", JSON.toJSONString(dto));
        return new RestResponse(dto);
    }

}
