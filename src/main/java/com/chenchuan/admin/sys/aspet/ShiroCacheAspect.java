package com.chenchuan.admin.sys.aspet;

import com.chenchuan.common.shiro.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ShiroCacheAspect {

    @Pointcut("execution(* com.chenchuan.admin.sys.controller.RoleController.index(..)) || " +
            "execution(* com.chenchuan.admin.sys.controller.UserController.*(..))")
    public void shiroCacheClearPointCut(){}

    @AfterReturning(returning = "a",pointcut = "shiroCacheClearPointCut()")
    public void clearShiroCache(Object a){
        System.out.println("clearCachedAuthorizationInfo");
        RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm myShiroRealm = (MyShiroRealm) realmSecurityManager.getRealms().iterator().next();
        myShiroRealm.clearCachedAuthorizationInfo();
    }
}
