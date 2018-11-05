package com.chenchuan.config.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * fastjson 整合配置
 */
@Configuration
public class FastjsonConfiguration implements WebMvcConfigurer {

    /**
     * 配置消息转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        //修改配置返回内容的过滤
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,//是否格式化返回值
                //SerializerFeature.DisableCircularReferenceDetect,//重复引用提示
                SerializerFeature.WriteMapNullValue//是否输出值为null的字段,默认为false
                //SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                //SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null，输出为false，而不是null
                //SerializerFeature.WriteNullNumberAsZero,//数值字段如果为null,输出为0,而非null
                //SerializerFeature.WriteNullStringAsEmpty//字符类型字段如果为null,输出为"",而非null
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(config);

        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastJsonHttpMessageConverter);
    }
}
