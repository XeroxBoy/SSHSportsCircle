package com.cdut.sx.utils;

import java.util.UUID;

/**
 * @author LeeB
 * 生成随机字符串工具类
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");

    }

}
