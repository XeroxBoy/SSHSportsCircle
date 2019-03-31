package com.cdut.sx.dao;

import com.cdut.sx.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Messagedao extends JpaRepository<Message, Integer> {

    @Query(value = "select from Message where user_id=:userId", nativeQuery = true)
    public List<Message> findByUser(@Param("userId") String userId);

    @Query(value = "select count(*) from Message where user_id=:userId", nativeQuery = true)
    public int findMyCount(@Param("userId") String userId); //看我发了多少条记录

    @Query(value = "select count(*) from Message where belong_to=:area", nativeQuery = true)
    public int findAreaCount(@Param("area") String area);//看每个圈子下面有多少状态


}
