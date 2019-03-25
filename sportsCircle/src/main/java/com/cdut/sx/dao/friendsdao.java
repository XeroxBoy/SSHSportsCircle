package com.cdut.sx.dao;

import com.cdut.sx.pojo.friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface friendsdao extends JpaRepository<friends, Integer> {
    @Query(value = "select * from friends where friendsFrom=:me", nativeQuery = true)
    public ArrayList<friends> queryMyFriends(@Param("me") String me);//查询朋友


//    public void update(String friend1, String friends2, boolean isCancel);//更新朋友


//    PageBean<user> findByPage(Integer currPage);

    //    List<friends> findByPage(int begin, int pagesize);
    @Query(value = "delete from friends where friendsFrom=?1 and friendsTo=?2")
    public void delete(String friends1, String friend2);//删除朋友
}

 