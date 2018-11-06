package com.chenchuan.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 散列加密类
 */
public class HashedUtil {

    /**
     * 密码加密
     *
     * @param userName         用户名
     * @param password         密码
     * @param hashAlgorithName 加密类型
     * @param hashIterations   散列次数
     * @return
     */
    public static SimpleHash getSimpleHash(String userName, String password, String hashAlgorithName, int hashIterations) {
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, ByteSource.Util.bytes(userName + "salt"), hashIterations);
        return simpleHash;
    }
}
