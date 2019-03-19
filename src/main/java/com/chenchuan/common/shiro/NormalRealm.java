package com.chenchuan.common.shiro;

import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.config.shiro.SecurityConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * 用户名密码登录realm
 */
public class NormalRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserDao userDao;

    @Autowired
    @Lazy
    private SecurityConfig securityConfig;

    @Autowired
    @Lazy
    private AuthorizingRealmMethod authorizingRealmMethod;

    /**
     * 授权
     *
     * @param principalCollection 要授权的用户（一般指当前用户）
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return authorizingRealmMethod.doGetAuthorizationInfo(principalCollection);
    }

    /**
     * 认证
     *
     * @param authenticationToken 要认证的信息
     * @return 登录比对信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录是输入的用户
        String loginName = (String) authenticationToken.getPrincipal();
        UserVo userVo = new UserVo();
        userVo.setLoginName(loginName);
        userVo.setStatus(1);
        UserPo userPo = userDao.findUserByLoginNameAndPassword(userVo);

        //如果没有用户
        if (userPo == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                userPo,
                userPo.getPassword(),
                ByteSource.Util.bytes(loginName + securityConfig.getSaltSuffix()),
                getName()
        );
    }

    /**
     * 清理权限缓存
     */
    public void clearCachedAuthorizationInfo() {
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
