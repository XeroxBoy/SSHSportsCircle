package com.cdut.sx.dao;

import com.cdut.sx.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Messagedao extends JpaRepository<Message, Integer> {

    @Query(value = "select from message where user_id=:userId", nativeQuery = true)
    List<Message> findByUser(@Param("userId") String userId);

    @Query(value = "select count(*) from message where user_id=:userId", nativeQuery = true)
    int findMyCount(@Param("userId") String userId); //看我发了多少条记录

}
