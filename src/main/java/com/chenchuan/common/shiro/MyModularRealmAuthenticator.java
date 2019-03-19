package com.chenchuan.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义realm管理器
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 自定义多realm认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的验证策略
        MyUsernamePasswordToken myUsernamePasswordToken = (MyUsernamePasswordToken) authenticationToken;
        //登录方式
        String loginType = myUsernamePasswordToken.getLoginType();
        //查找所有的realm
        Collection<Realm> realms = getRealms();
        //登录方式对应的realm
        List<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType)) {
                typeRealms.add(realm);
            }
        }
        //判断是否多realm
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.get(0), myUsernamePasswordToken);
        } else {
            return doMultiRealmAuthentication(typeRealms, myUsernamePasswordToken);
        }
    }
}
