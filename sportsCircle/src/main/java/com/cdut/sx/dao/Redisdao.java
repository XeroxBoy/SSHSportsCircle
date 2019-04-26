package com.cdut.sx.dao;

public interface Redisdao {


    boolean set(String key, String value);


    String get(String key);


    boolean expire(String key, long expire);


    boolean remove(String key);

}