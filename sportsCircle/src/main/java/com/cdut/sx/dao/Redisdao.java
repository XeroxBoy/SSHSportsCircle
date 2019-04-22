package com.cdut.sx.dao;

public interface Redisdao {

    /**
     * set存数据
     */
    boolean set(String key, String value);

    /**
     * get获取数据
     */
    String get(String key);

    /**
     * 设置有效天数
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     */
    boolean remove(String key);

}