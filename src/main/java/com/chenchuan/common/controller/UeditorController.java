package com.chenchuan.common.controller;

import com.chenchuan.common.service.UeditorService;
import com.chenchuan.common.util.UeditorReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ueditor Controller
 */
@Controller
@RequestMapping("/common/ueditor")
public class UeditorController extends BaseController {

    @Autowired
    private UeditorService ueditorService;


    /**
     * ueditor编辑页面
     *
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "/common/depend/_ueditor";
    }

    /**
     * 上传文件
     *
     * @param upfile
     * @return ueditor需要的格式
     */
    @PostMapping("/uploadFileByUE")
    @ResponseBody
    public UeditorReturnUtil uploadFileByUE(MultipartFile upfile) throws IOException {
        return ueditorService.uploadFileByUE(upfile);
    }

    /**
     * 上传涂鸦
     *
     * @param upfile base64加密的字符串
     * @return ueditor需要的格式
     */
    @PostMapping("/uploadscrawlByUE")
    @ResponseBody
    public UeditorReturnUtil uploadscrawlByUE(String upfile) throws IOException {
        return ueditorService.uploadscrawlByUE(upfile);
    }
}
