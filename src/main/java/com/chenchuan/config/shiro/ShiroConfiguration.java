package com.chenchuan.config.shiro;

import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.chenchuan.common.shiro.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

//    @Autowired(required = false)
//    private PermissionService permissionService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, PermissionService permissionService) {
        //定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/admin/guest/login/index");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");//------删除，项目不打算用
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/index");

        //顺序拦截器配置,LinkedHashMap是有序的
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/admin/guest/login/index", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/guest/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugin/**", "anon");
        //配置url访问权限
        //获取所有权限
        List<PermissionPo> permissionList = permissionService.findPermissionsList(new PermissionVo());
        if (!(permissionList == null && permissionList.size() == 0)) {
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
        return new MyShiroRealm();
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
        return securityManager;
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
