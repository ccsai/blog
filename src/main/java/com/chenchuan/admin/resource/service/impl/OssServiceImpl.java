package com.chenchuan.admin.resource.service.impl;

import com.chenchuan.admin.resource.dao.OssDao;
import com.chenchuan.admin.resource.po.OssPo;
import com.chenchuan.admin.resource.service.OssService;
import com.chenchuan.common.service.QiniuFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * oss service实现
 */
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssDao ossDao;

    @Autowired
    private QiniuFileService qiniuFileService;


    @Override
    @Transactional
    public int removeJunkFromOssAndDB() {
        //获取垃圾数据
        List<OssPo> ossList = ossDao.findOssJunkData();
        if (ossList != null && ossList.size() > 0) {
            //要刪除的oss keys集合
            List<String> ossKeysList = new ArrayList<>();
            //获取oss条数
            int ossLength = ossList.size();
            //将oss key分在多个数组中，每个数组不超过1000,算出可分为几组
            int groupNumber = ossLength / 1000;
            //（不被1000整除时）最后一组数量
            int endGroupKeyNumber = ossLength % 1000;
            //每一组起始oss
            int index = 0;
            if (groupNumber > 0) {
                for (int i = 1; i <= groupNumber; i++) {
                    String[] keysArr = new String[1000];
                    for (int j = 0; j < 999; j++) {
                        String ossKey = ossList.get(index).getOssKey();
                        keysArr[j] = ossKey;
                        ossKeysList.add(ossKey);
                        index++;
                    }
                    //删除云oss
                    qiniuFileService.deleteFiles(keysArr);
                }
            }
            //（不被1000整除时）最后一组oss的删除
            if (endGroupKeyNumber > 0) {
                String[] endKeysArr = new String[endGroupKeyNumber];
                for (int i = 0; i < endGroupKeyNumber; i++) {
                    String ossKey = ossList.get(index).getOssKey();
                    endKeysArr[i] = ossKey;
                    ossKeysList.add(ossKey);
                    index++;
                }
                //删除云oss
                qiniuFileService.deleteFiles(endKeysArr);
            }
            //删除oss表的垃圾数据
            ossDao.removeOssJunkData(ossKeysList);
        }
        return 1;
    }
}
