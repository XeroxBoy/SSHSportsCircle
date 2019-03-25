package com.cdut.sx.dao;

import com.cdut.sx.pojo.comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface Commentsdao extends JpaRepository<comments, Integer> {
    @Query(value = "select * from comments", nativeQuery = true)
    public ArrayList<comments> queryAll();//查询所有人

    @Modifying
    @Query(value = "delete from comments where commentId=:id", nativeQuery = true)
    public void delete(@Param("id") int commentId);//删除评论

    @Query(value = "select * from comments where messagebelongTo=:message", nativeQuery = true)
    List queryByMsgId(@Param("message") String messagename);//查这个msg的评论

    @Query(value = "select * from comments where commentId=:id", nativeQuery = true)
    List<comments> queryById(@Param("id") int commentId);//根据评论ID查评论

}
