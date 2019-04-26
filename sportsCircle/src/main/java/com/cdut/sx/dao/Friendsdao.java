package com.cdut.sx.dao;

import com.cdut.sx.pojo.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface Friendsdao extends JpaRepository<Friends, Integer> {
    @Query(value = "select * from Friends where friends_from=:me", nativeQuery = true)
    ArrayList<Friends> queryMyFriends(@Param("me") String me);//查询朋友

    @Query(value = "delete from Friends where friends_from=?1 and friends_to=?2")
    void delete(String friends1, String friend2);//删除朋友
}

 