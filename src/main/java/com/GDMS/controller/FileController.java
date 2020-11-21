package com.GDMS.controller;

import com.GDMS.rest.RestResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Api(tags = {"测试文件上传（图片）、图片清晰度测评"})
@RestController
@RequestMapping(value = "/uploadFile")
public class FileController {

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public RestResponse file() {
        return new RestResponse("file");
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public RestResponse fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
    //  model.addAttribute("filename", filename);
        return RestResponse.SUCCESS;
    }




   /* *//**
     * 图像清晰评价算法
     * @return
     *//*
    @RequestMapping(value = "/pictureLegibilityEvaluation",method = RequestMethod.POST)
    @ApiOperation(value = "文件下的多个图片清晰度测评")
    public RestResponse pictureLegibilityEvaluation(String path) {
        List<String> values = new ArrayList<>();
        //要遍历的路径
        //String path = "D:\\temp-rainy";
        //获取其file对象
        File file = new File(path);
        //遍历path下的文件和目录，放在File数组中
        File[] fs = file.listFiles();
        Mat srcImage = null;
        //遍历File[]数组
        for (File f : fs) {
            //若非目录(即文件)，则打印
            if (!f.isDirectory())
                srcImage = imread(f.getAbsolutePath());
            Mat dstImage = new Mat();
            //转化为灰度图
            cvtColor(srcImage, dstImage, COLOR_BGR2GRAY);
            //在gray目录下生成灰度图片
            imwrite(path+"gray//gray-"+f.getName(), dstImage);
            Mat laplacianDstImage = new Mat();
            //阈值太低会导致正常图片被误断为模糊图片，阈值太高会导致模糊图片被误判为正常图片
            Laplacian(dstImage, laplacianDstImage, CV_64F);
            //在laplacian目录下升成经过拉普拉斯掩模做卷积运算的图片
            imwrite(path+"laplacian//laplacian-"+f.getName(), laplacianDstImage);
            //矩阵标准差
            Mat stddev = new Mat();
            //求矩阵的均值与标准差
            meanStdDev(laplacianDstImage, new Mat(), stddev);
            //标准差越大图像越清晰；
           values.add(f.getName() + "矩阵的标准差：" + stddev.createIndexer().getDouble());
        }
        return new RestResponse(values);
    }

    @RequestMapping(value = "/onePictureLegibilityEvaluation",method = RequestMethod.POST)
    @ApiOperation(value = "单个图片清晰度测评")
    public RestResponse onePictureLegibilityEvaluation(String path) {
        String value = null;
        //要遍历的路径
        //String path = "D:\\temp-rainy\\dinner.jpg";
        //获取其file对象
        File file = new File(path);
        Mat srcImage = null;
            //若非目录(即文件)，则打印
            if (!file.isDirectory())
                srcImage = imread(file.getAbsolutePath());
            Mat dstImage = new Mat();
            //转化为灰度图
            cvtColor(srcImage, dstImage, COLOR_BGR2GRAY);
            //在gray目录下生成灰度图片
            imwrite(path+"gray//gray-"+file.getName(), dstImage);
            Mat laplacianDstImage = new Mat();
            //阈值太低会导致正常图片被误断为模糊图片，阈值太高会导致模糊图片被误判为正常图片
            Laplacian(dstImage, laplacianDstImage, CV_64F);
            //在laplacian目录下升成经过拉普拉斯掩模做卷积运算的图片
            imwrite(path+"laplacian//laplacian-"+file.getName(), laplacianDstImage);
            //矩阵标准差
            Mat stddev = new Mat();
            //求矩阵的均值与标准差
            meanStdDev(laplacianDstImage, new Mat(), stddev);
            //标准差越大图像越清晰；
            value =  file.getName() + "矩阵的标准差：" + stddev.createIndexer().getDouble();
            DecimalFormat df = new DecimalFormat("#.00");
            Double valueOf = Double.valueOf(df.format(stddev.createIndexer().getDouble()));

            Indexer indexer = stddev.createIndexer();
            String string = String.valueOf(indexer);
            StringBuffer sb = new StringBuffer(string);
            sb.deleteCharAt(0).deleteCharAt(sb.length()-1);
            Double aDouble = Double.valueOf(df.format(Double.valueOf(sb.toString())));
        return new RestResponse(valueOf);
    }*/


}
