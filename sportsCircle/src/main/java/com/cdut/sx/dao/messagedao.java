package com.cdut.sx.dao;

import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.message;

import java.util.ArrayList;
import java.util.List;

public interface messagedao {

    public ArrayList<message> queryAll();//查询所有人

    public List<message> queryById(int messageid);//按message编号查message

    public void save(message message);//保存状态

    public void update(message message);//更新状态

    public void delete(message message);//删除状态

    public PageBean<message> findByPage(Integer currPage);//查找这一页的状态

    public PageBean<message> findMineByPage(Integer currPage, String userId);//查找我的状态中这一页的状态

    PageBean<message> findByArea(Integer currPage, String area);//查找圈子的状态中第X页的状态

    List<message> queryByUserId(String username);//查这个人的message

    public int findMyCount(String userId); //看我发了多少条记录

    public int findCount();//查所有的状态数

    public List<message> findByPageArea(int begin, int pagesize, String area);//跟据页数和圈子筛选

    public int findAreaCount(String area);//看每个圈子下面有多少状态


}
