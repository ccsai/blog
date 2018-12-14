package com.chenchuan.admin.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenchuan.common.service.QiniuFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/testa")
public class UploadFileTestController {

    @Autowired
    private QiniuFileService qiniuFileService;

    @GetMapping("/upIndex")
    public String upIndex() {
        return "/admin/test/uploadFile";
    }

    @PostMapping("/upFile")
    @ResponseBody
    public JSONObject upFile(MultipartFile file) throws IOException {
        try {
            return qiniuFileService.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/removeFile")
    @ResponseBody
    public int upFile(String key) {
        return qiniuFileService.deleteFile(key);
    }

    @PostMapping("/removeFiles")
    @ResponseBody
    public int removeFiles(@RequestParam(value = "keysList[]",required = false) String[] keysList) {
        return qiniuFileService.deleteFiles(keysList);
    }
}
