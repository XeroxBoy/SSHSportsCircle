package com.cdut.sx.dao;

import com.cdut.sx.pojo.comments;

import java.util.ArrayList;
import java.util.List;

public interface Commentsdao {

    public ArrayList<comments> queryAll();//查询所有人

    public void save(comments comments);//保存评论

    public void update(comments comments);//更新评论

    public void delete(comments comments);//删除评论

    List queryByMsgId(String messagename);//查这个msg的评论

    List<comments> queryById(int commentId);//根据评论ID查评论

}
