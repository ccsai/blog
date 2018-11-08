package com.chenchuan.config.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 在freemarker中使用shiro标签的配置
 */
@Component
public class ShiroTagFreeMarkerConfigurer {

    @Autowired
    private Configuration configuration;


    /**
     * ftl可以使用shiro标签
     *
     * @throws TemplateModelException
     */
    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        //ftl可以使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
