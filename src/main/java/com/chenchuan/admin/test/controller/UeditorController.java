package com.chenchuan.admin.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenchuan.common.controller.BaseController;
import com.chenchuan.common.service.QiniuFileService;
import com.chenchuan.common.util.UeditorReturnUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * ueditor Controller
 */
@Controller
@RequestMapping("/common/ueditor")
public class UeditorController extends BaseController {

    @Autowired
    private QiniuFileService qiniuFileService;


    /**
     * ueditor编辑页面
     *
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "/admin/test/ueditor";
    }

    @PostMapping("/uploadImageByUE")
    @ResponseBody
    public UeditorReturnUtil uploadimage(MultipartFile upfile) {
        //System.out.println(upfile.getName());
        UeditorReturnUtil ueditorConfig = new UeditorReturnUtil();
        Map<String, Object> rs = new HashMap<>();
        try {
            JSONObject res = qiniuFileService.uploadFile(upfile.getInputStream());

            ueditorConfig.setState("SUCCESS");
            ueditorConfig.setUrl(res.getString("cdnPrefix") + "/" + res.getString("key"));
            ueditorConfig.setTitle(res.getString("key"));
            ueditorConfig.setOriginal(res.getString("key"));
//            rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
//            rs.put("url", res.getString("cdnPrefix") + "/" + res.getString("key")); // 能访问到你现在图片的路径
//            rs.put("title", res.getString("key"));
//            rs.put("original", res.getString("key"));
        } catch (Exception e) {
            e.printStackTrace();
            ueditorConfig.setState("文件上传失败...");
//            rs.put("state", "文件上传失败!"); // 在此处写上错误提示信息，这样当错误的时候就会显示此信息
//            rs.put("url", "");
//            rs.put("title", "");
//            rs.put("original", "");
        }

        return ueditorConfig;
    }

    @PostMapping("/uploadscrawl")
    @ResponseBody
    public UeditorReturnUtil uploadvideoByUE(String upfile) {
        UeditorReturnUtil ueditorConfig = new UeditorReturnUtil();
        Map<String, Object> rs = new HashMap<>();
        try {
            //创建解密 BASE64Decoder
            BASE64Decoder base64Decoder = new BASE64Decoder();
            //解密字符串转字节数组
            byte [] bs = base64Decoder.decodeBuffer(upfile.trim());
            JSONObject res = qiniuFileService.uploadFile(new ByteArrayInputStream(bs));
            ueditorConfig.setState("SUCCESS");
            ueditorConfig.setUrl(res.getString("cdnPrefix") + "/" + res.getString("key"));
            ueditorConfig.setTitle(res.getString("key"));
            ueditorConfig.setOriginal(res.getString("key"));
        } catch (Exception e) {
            e.printStackTrace();
            ueditorConfig.setState("文件上传失败...");
        }
        return ueditorConfig;
    }

    @RequestMapping("/test")
    @ResponseBody
    public UeditorReturnUtil test() {
        return null;
    }
}
