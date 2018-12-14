package com.chenchuan.common.util;

/**
 * ueditor配置
 */
public class UeditorReturnUtil {

    /**
     * 返回提示信息
     */
    private String state;

    /**
     * 文件访问路径
     */
    private String url;

    /**
     * 文件标题
     */
    private String title;

    /**
     * 文件名
     */
    private String original;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
