package com.cdut.sx.dao;

import com.cdut.sx.pojo.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Commentsdao extends JpaRepository<Comments, Integer> {
    @Query(value = "select * from Comments", nativeQuery = true)
    List<Comments> queryAll();//查询所有人

    @Modifying
    @Query(value = "delete from Comments where comment_id=:id", nativeQuery = true)
    void delete(@Param("id") int commentId);//删除评论

    @Query(value = "select * from Comments where messagebelong_to=:message", nativeQuery = true)
    List queryByMsgId(@Param("message") String messagename);//查这个msg的评论

    @Query(value = "select * from Comments where comment_id=:id", nativeQuery = true)
    List<Comments> queryById(@Param("id") int commentId);//根据评论ID查评论

}
