package com.cdut.sx.dao;

import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.friends;
import com.cdut.sx.pojo.user;

import java.util.ArrayList;
import java.util.List;

public interface friendsdao {
    public ArrayList<friends> queryMyFriends(String me);//查询朋友

    public void save(String friend1, String friend2);//保存朋友

    public void update(String friend1, String friends2, boolean isCancel);//更新朋友

    int findCount();

    PageBean<user> findByPage(Integer currPage);

    List<friends> findByPage(int begin, int pagesize);

    public void delete(String friends1, String friend2);//删除朋友
}

 