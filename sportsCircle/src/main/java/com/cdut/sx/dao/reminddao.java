package com.cdut.sx.dao;

import com.cdut.sx.pojo.remind;

import java.util.ArrayList;

public interface reminddao {

    public ArrayList<remind> queryAll(String user);//查询所有邀约

    public void save(remind remind);//保存邀约

    public void update(remind remind);//更新邀约

    public void delete(remind remind);//删除邀约
}

 