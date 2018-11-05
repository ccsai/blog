package com.chenchuan.common.util;

import java.util.UUID;

/**
 * uuid工具类
 */
public class UuidUtil {

    /**
     * 获取uuid
     *
     * @return uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("_", "").toLowerCase();
    }

    /**
     * 获取指定书怒数目的uuid
     *
     * @param number 指定数目
     * @return uuid数组
     */
    public static String[] getUuid(int number) {
        if (number < 1) {
            return null;
        }
        String[] uuid = new String[number];
        for (int i = 0; i < number; i++) {
            uuid[i] = getUuid();
        }
        return uuid;
    }
}

