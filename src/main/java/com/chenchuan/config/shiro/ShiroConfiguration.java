package com.chenchuan.config.shiro;

import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.common.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro 核心配置
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private SecurityConfig securityConfig;


    /**
     * shiro过滤链
     *
     * @param securityManager
     * @param permissionDao
     * @param securityConfig  安全配置
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, PermissionDao permissionDao, SecurityConfig securityConfig) {
        //定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(securityConfig.getAdminLoginPageUrl());
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl(securityConfig.getUnauthorizedUrl());

        //顺序拦截器配置,LinkedHashMap是有序的
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anon:所有url都都可以匿名访问
        //获取匿名访问地址数组
        String[] annoUrlPathArray = securityConfig.getAnonUrlPath();
        for (String annoUrlPath : annoUrlPathArray) {
            filterChainDefinitionMap.put(annoUrlPath, "anon");
        }
        //配置url访问权限
        //获取所有权限
        List<PermissionPo> permissionList = permissionDao.findAllPermissions();
        if (permissionList != null && permissionList.size() != 0) {
            for (PermissionPo p : permissionList) {
                filterChainDefinitionMap.put(p.getUrl(), "perms[" + p.getUrl() + ":request]");
            }
        }
        //authc:所有url都必须认证通过才可以访问
        filterChainDefinitionMap.put("/**", "authc");

        //设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 将自定义realm放入容器
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //加密
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher(securityConfig));
        return myShiroRealm;
    }

    /**
     * 配置安全管理器,主要配置realm认证
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(ehCacheManager(securityConfig));
        return securityManager;
    }

    /**
     * 凭证匹配器（SimpleAuthenticationInfo处理）
     *
     * @param securityConfig 安全配置
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(SecurityConfig securityConfig) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName(securityConfig.getHashAlgorithmName());
        //散列次数
        hashedCredentialsMatcher.setHashIterations(securityConfig.getHashIterations());
        return hashedCredentialsMatcher;
    }

    /**
     * shiro缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(SecurityConfig securityConfig) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile(securityConfig.getCacheManagerConfigFile());
        return ehCacheManager;
    }

    /**
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 加入shiro使用的支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
