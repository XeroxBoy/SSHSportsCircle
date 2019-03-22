package com.cdut.sx.dao;


import com.cdut.sx.pojo.user;

import java.util.ArrayList;
import java.util.List;

public interface Userdao {

    public ArrayList<user> queryAll();//查询所有人

    public void save(user user);//保存用户

    public void update(user user);//更新用户

    public void delete(user user);//删除用户

    public List<user> queryByName(String username);//根据ID查用户
//	public List<user> queryByCode(String code);//根据激活码查用户


}
