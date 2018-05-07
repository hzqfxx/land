package com.test.land.landparent.admin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.test.land.landparent.admin.Interceptor.AuthInterceptor;

/**
 * @author xiaox
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }


    /***
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).excludePathPatterns("/admin/user/login","/admin/user/register","/admin/user/export","/admin/user/export3","/admin/user/import","/admin/car/import2",
                "/admin/car/transfer2","/admin/user/transfer","/error","/admin/ip/*","/admin/url/*","/admin/user/add","/admin/user/getAll","/");
    }


    /**
     * 配置fastJson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       /* //转换消息对象
        FastJsonHttpMessageConverter4 fastJsonConverter = new FastJsonHttpMessageConverter4();
        //添加fastJson配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        *//**
         * 设置返回信息
         * PrettyFormat,//结果是否格式化,默认为false
         * WriteMapNullValue,//是否输出值为null的字段,默认为false
         * WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
         *
         *//*
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,SerializerFeature.DisableCircularReferenceDetect);

        *//**
         * 反序列化特性的配置，同序列化的feature是通过int的位或来实现其特性开启还是关闭的。
         * 默认配置是： AutoCloseSource | UseBigDecimal | AllowUnQuotedFieldNames | AllowSingleQuotes | AllowArbitraryCommas | AllowArbitraryCommas | SortFeidFastMatch | IgnoreNotMatch ，
         * 表示检查json串的完整性 and 转换数值使用BigDecimal and 允许接受不使用引号的filedName and 允许接受使用单引号的key和value and
         * 允许接受连续多个","的json串 and 使用排序后的field做快速匹配 and 忽略不匹配的key/value对。
         * 当然，这些参数也是可以通过其他途径配置的。
         *
         *//*
        fastJsonConfig.setFeatures(Feature.AllowArbitraryCommas,Feature.AllowUnQuotedFieldNames);

        fastJsonConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastJsonConverter);*/

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
       /* supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);*/
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig =new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(0,fastJsonHttpMessageConverter);
    }
}
