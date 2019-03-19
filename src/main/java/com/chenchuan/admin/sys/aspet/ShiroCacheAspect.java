package com.chenchuan.admin.sys.aspet;

import com.chenchuan.common.shiro.NormalRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * shiro缓存切面
 */
@Aspect
@Component
public class ShiroCacheAspect {

    @Pointcut("execution(* com.chenchuan.admin.sys.controller.PermissionController.add*(..)) || " +
            "execution(* com.chenchuan.admin.sys.controller.PermissionController.edit*(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.PermissionController.remove*(..))||" +
            "execution(* com.chenchuan.admin.sys.controller.RoleController.add*(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.RoleController.edit*(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.RoleController.remove*(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.RoleController.authorizationMenuPermission(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.RoleController.addRoleCommonPermissionAuth(..)) ||" +
            "execution(* com.chenchuan.admin.sys.controller.UserController.addUserRoleAuth(..))")
    public void shiroCacheClearPointCut() {
    }

    /**
     * 角色权限改变时权限配置刷新
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "shiroCacheClearPointCut()")
    public void clearShiroCache(Map<String, Object> result) {
        if (result.get("resultCode") != null && result.get("resultCode").equals(1)) {
            RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            NormalRealm normalRealm = (NormalRealm) realmSecurityManager.getRealms().iterator().next();
            normalRealm.clearCachedAuthorizationInfo();
        }
    }
}
