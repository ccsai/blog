package com.chenchuan.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chenchuan.admin.resource.dao.OssDao;
import com.chenchuan.admin.resource.vo.OssVo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.service.QiniuFileService;
import com.chenchuan.common.service.UeditorService;
import com.chenchuan.common.util.UeditorReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * ueditor service实现
 */
@Service
public class UeditorServiceImpl implements UeditorService {

    @Autowired
    private QiniuFileService qiniuFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private OssDao ossDao;


    @Override
    public UeditorReturnUtil uploadFileByUE(MultipartFile upfile) throws IOException {
        //ueditor上传返回格式
        UeditorReturnUtil ueditorConfig = new UeditorReturnUtil();
        //七牛上传
        JSONObject qiniuUpResult = qiniuFileService.uploadFile(upfile.getInputStream());
        if (qiniuUpResult == null) {
            ueditorConfig.setState("文件上传失败...");
        } else {
            //oss key
            String ossKey = qiniuUpResult.getString("key");
            ueditorConfig.setState("SUCCESS");
            ueditorConfig.setUrl(qiniuUpResult.getString("cdnPrefix") + ossKey);
            ueditorConfig.setTitle(ossKey);
            ueditorConfig.setOriginal(ossKey);
            //保存上传oss信息到数据库
            OssVo ossVo = new OssVo();
            ossVo.setOssKey(ossKey);
            ossVo.setOssSize(upfile.getSize());
            ossVo.setOssMimeType(upfile.getContentType());
            //当前登录用户
            String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
            ossVo.setCreateUser(userId);
            ossVo.setModifyUser(userId);
            ossDao.addOss(ossVo);
        }
        return ueditorConfig;
    }

    @Override
    public UeditorReturnUtil uploadscrawlByUE(String upfile) throws IOException {
        //ueditor上传返回格式
        UeditorReturnUtil ueditorConfig = new UeditorReturnUtil();
        //创建解密 BASE64Decoder
        BASE64Decoder base64Decoder = new BASE64Decoder();
        //解密字符串转字节数组
        byte[] bs = base64Decoder.decodeBuffer(upfile.trim());
        //获取
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs);
        //七牛上传
        JSONObject qiniuUpResult = qiniuFileService.uploadFile(byteArrayInputStream);
        if (qiniuUpResult == null) {
            ueditorConfig.setState("文件上传失败...");
        } else {
            //oss key
            String ossKey = qiniuUpResult.getString("key");
            ueditorConfig.setState("SUCCESS");
            ueditorConfig.setUrl(qiniuUpResult.getString("cdnPrefix") + ossKey);
            ueditorConfig.setTitle(ossKey);
            ueditorConfig.setOriginal(ossKey);
            //保存上传oss信息到数据库
            OssVo ossVo = new OssVo();
            ossVo.setOssKey(ossKey);
            ossVo.setOssSize((long) bs.length);
            ossVo.setOssMimeType("image/png");
            //当前登录用户
            String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
            ossVo.setCreateUser(userId);
            ossVo.setModifyUser(userId);
            ossDao.addOss(ossVo);
        }
        return ueditorConfig;
    }
}
