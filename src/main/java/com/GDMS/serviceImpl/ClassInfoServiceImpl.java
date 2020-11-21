package com.GDMS.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.GDMS.dto.ClassInfoExDto;
import com.GDMS.dto.ClassInfoReqDto;
import com.GDMS.entity.ClassInfoEntity;
import com.GDMS.mapper.ClassInfoMapper;
import com.GDMS.rest.RestResponse;
import com.GDMS.service.ClassInfoService;
import com.GDMS.utils.SnowFlake;
import com.GDMS.utils.exception.BusinessException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {


    @Resource
    ClassInfoMapper classInfoMapper;

    @Override
    public RestResponse queryClassInfo(ClassInfoReqDto dto) {

        PageHelper.startPage(dto.getCurrentPage(),dto.getPageSize());
        List<ClassInfoEntity> classInfos = classInfoMapper.findAllClassInfo(dto);
        PageInfo<ClassInfoEntity> classInfoEntityPageInfo = new PageInfo<>(classInfos);
        return new RestResponse(classInfoEntityPageInfo);
    }

    @Override
    public int insert(List<ClassInfoEntity> entities) {

        for (ClassInfoEntity entity : entities) {
            entity.setId(SnowFlake.nextId());
        }
        return classInfoMapper.insert(entities);
    }

    @Override
    public RestResponse exportClassInfo(ClassInfoReqDto dto){
        Date start = new Date();
        StringBuilder sb = new StringBuilder();
        PageHelper.startPage(dto.getCurrentPage(),dto.getPageSize());
        List<ClassInfoEntity> classInfos = classInfoMapper.findAllClassInfo(dto);
        PageInfo<ClassInfoEntity> classInfoEntityPageInfo = new PageInfo<>(classInfos);
        List<ClassInfoExDto> exDtos = new ArrayList<>();
        Integer num=1;
        for (ClassInfoEntity classInfo : classInfos) {
            ClassInfoExDto exDto = new ClassInfoExDto();
            BeanUtils.copyProperties(classInfo,exDto);
            exDto.setNum(num);
            exDtos.add(exDto);
            num++;
        }
        Workbook workbook = ExcelExportUtil.exportExcel( new ExportParams("导出教室信息", null, "导出教室信息"),
                ClassInfoExDto.class, exDtos);
        System.out.println(new Date().getTime() - start.getTime());

        try {
            File file = new File("D:/excel/");
            if (!file.exists()) {
                file.mkdirs();
            }
            StringBuilder excelName = new StringBuilder();
            excelName.append("D:/excel/教室信息导出测试-").append(new SimpleDateFormat("yyyy-MM-dd  HH：mm：ss").format(new Date())).append(".xls");
            FileOutputStream fos = new FileOutputStream(excelName.toString());
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            sb.append("导出失败！").append(e.toString());
            throw new BusinessException(sb.toString());
        }
        return RestResponse.SUCCESS;
    }


}
