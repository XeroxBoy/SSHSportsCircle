package com.cdut.sx.dao;

import com.cdut.sx.pojo.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface messagedao extends JpaRepository<message, Integer> {

    @Query(value = "select from message where userId=:userId", nativeQuery = true)
    public List<message> findByUser(@Param("userId") String userId);

    @Query(value = "select count(*) from message where userId=:userId", nativeQuery = true)
    public int findMyCount(@Param("userId") String userId); //看我发了多少条记录

    @Query(value = "select count(*) from message where belongTo=:area", nativeQuery = true)
    public int findAreaCount(@Param("area") String area);//看每个圈子下面有多少状态


}
