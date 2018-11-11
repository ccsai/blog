package com.chenchuan.admin.index.service;

import java.util.Map;

/**
 * 后台首页service
 */
public interface IndexService {

    /**
     * 加载首页组件和数据
     *
     * @return 页面所需组件
     */
    Map<String, Object> loadIndexPageData();

}
