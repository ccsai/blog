package com.chenchuan.common.shiro;

import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * realm所需公共方法
 */
@Component
public class AuthorizingRealmMethod {

    @Autowired
    private RoleService roleService;


    /**
     * 授权
     *
     * @param principalCollection 要授权的用户（一般指当前用户）
     * @return 授权信息
     */
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //权限角色信息
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        //获取当前用户
        UserPo userPo = (UserPo) principalCollection.getPrimaryPrincipal();
        //当前用户的角色权限
        UserVo userVo = new UserVo();
        userVo.setLoginName(userPo.getLoginName());
        List<RolePo> roleList = roleService.findRolesPermissionsByUser(userVo);
        //完成授权
        if (roleList != null && roleList.size() != 0) {
            for (RolePo r : roleList) {
                simpleAuthenticationInfo.addRole(r.getRoleName());
                List<PermissionPo> permissionList = r.getPermissionList();
                if (permissionList != null && permissionList.size() != 0) {
                    for (PermissionPo p : permissionList) {
                        simpleAuthenticationInfo.addStringPermission(p.getUrl() + ":request");
                    }
                }
            }
        }
        return simpleAuthenticationInfo;
    }
}
