package com.chenchuan.common.service;

import com.chenchuan.common.util.UeditorReturnUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ueditor service
 */
public interface UeditorService {

    /**
     * 上传文件
     *
     * @param upfile
     * @return ueditor需要的格式
     */
    UeditorReturnUtil uploadFileByUE(MultipartFile upfile) throws IOException;

    /**
     * 上传涂鸦
     *
     * @param upfile base64加密的字符串
     * @return ueditor需要的格式
     */
    UeditorReturnUtil uploadscrawlByUE(String upfile) throws IOException;
}
