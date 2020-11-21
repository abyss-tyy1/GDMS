package com.GDMS.controller;

import com.GDMS.rest.RestResponse;
import com.GDMS.utils.BASE64;
import com.GDMS.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = {"测试---使用百度api识别身份证信息---"})
@RestController
@RequestMapping(value = "/BaiDuOCR")
public class BaiDuOCR {

    public static String getAuth() {
        // 官网获取的 API Key
        String clientId = "ZY1KTZTpCqxDUhlaba5wC9Gq";
        // 官网获取的 Secret Key
        String clientSecret = "LZ0yAc4gafQZLUaxCRxPxLTu4Y7qUcLI";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取token
     * @param ak
     * @param sk
     * @return
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //百度推荐使用POST请求
            connection.setRequestMethod("POST");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.out.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }


    /**
     * 调用OCR
     * @param httpUrl
     * @param httpArg
     * @return
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            //用java JDK自带的URL去请求
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            //设置该请求的消息头
            //设置HTTP方法：POST
            connection.setRequestMethod("POST");
            //设置其Header的Content-Type参数为application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "uml8HFzu2hFd8iEG2LkQGMxm");
            //将第二步获取到的token填入到HTTP header
            connection.setRequestProperty("access_token", BaiDuOCR.getAuth());
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes(StandardCharsets.UTF_8));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *身份证参数转换
     * @param jsonResult
     * @return
     */
    public static HashMap<String, String> getHashMapByJson(String jsonResult) {
        HashMap map = new HashMap<String, String>();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONObject words_result = jsonObject.getJSONObject("words_result");
            Iterator<String> it = words_result.keys();
            while (it.hasNext()) {
                String key = it.next();
                JSONObject result = words_result.getJSONObject(key);
                String value = result.getString("words");
                switch (key) {
                    case "姓名":
                        map.put("姓名", value);
                        break;
                    case "民族":
                        map.put("民族", value);
                        break;
                    case "住址":
                        map.put("住址", value);
                        break;
                    case "公民身份号码":
                        map.put("公民身份号码", value);
                        break;
                    case "出生":
                        Date birthday = new SimpleDateFormat("yyyyMMdd").parse(value);
                        map.put("出生日期", simpleDateFormat.format(birthday));
                        break;
                    case "性别":
                        map.put("性别", value);
                        break;
                    case "失效日期":
                        Date idCardEndDate = new SimpleDateFormat("yyyyMMdd").parse(value);
                        map.put("失效日期",  simpleDateFormat.format(idCardEndDate));
                        break;
                    case "签发日期":
                        Date idCardBeginDate = new SimpleDateFormat("yyyyMMdd").parse(value);
                        map.put("签发日期", simpleDateFormat.format(idCardBeginDate));
                        break;
                    case "签发机关":
                        map.put("签发机关", value);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/getIDCard",method = RequestMethod.POST)
    public RestResponse getIDCard(@RequestParam(value = "身份证绝对路径") String jpgPath,
                                  @RequestParam(value = "0为正面，1为反面") Integer sides) {

     //获取本地的绝对路径图片
       // File file = new File("D:\\temp-rainy\\obm.png");
        File file = new File(jpgPath);
        //进行BASE64位编码
        String imageBase = BASE64.encodeImgageToBase64(file);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        //百度云的文字识别接口,后面参数为获取到的token
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+BaiDuOCR.getAuth();
        //id_card_side=front 识别正面    id_card_side=back  识别背面
        String IdCardSides = null;
        if (sides==0){
            IdCardSides="id_card_side=front";
        }else if (sides == 1){
            IdCardSides="id_card_side=back";
        }else {
            throw new BusinessException("sides只能是0或者1 ！");
        }

        String httpArg = "detect_direction=true&" + IdCardSides + "&image=" + imageBase;
        String jsonResult = BaiDuOCR.request(httpUrl, httpArg);
        //System.out.println("返回的结果--------->" + jsonResult);
        HashMap<String, String> map = BaiDuOCR.getHashMapByJson(jsonResult);
        for (String key : map.keySet()) {
            System.out.println(key +": "+ map.get(key));
        }


        return new RestResponse(map);
    }

}
