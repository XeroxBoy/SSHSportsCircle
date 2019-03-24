package com.cdut.sx.dao;

import com.cdut.sx.pojo.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface messagedao extends JpaRepository<message,Integer> {

//findAll
 //   public ArrayList<message> queryAll();//查询所有人

//    public PageBean<message> findByPage(Integer currPage);//查找这一页的状态
//
//    public PageBean<message> findMineByPage(Integer currPage, String userId);//查找我的状态中这一页的状态
//    PageBean<message> findByArea(Integer currPage, String area);//查找圈子的状态中第X页的状态

//    List<message> queryByUserId(String username);//查这个人的message
    @Query(value="select from message where userId=:userId",nativeQuery = true)
    public List<message> findByUser(@Param("userId") String userId);
    @Query(value="select count(*) from message where userId=:userId",nativeQuery = true)
    public int findMyCount(@Param("userId") String userId); //看我发了多少条记录
//    public List<message> findByPageArea(int begin, int pagesize, String area);//跟据页数和圈子筛选
@Query(value="select count(*) from message where belongTo=:area",nativeQuery = true)
    public int findAreaCount(@Param("area") String area);//看每个圈子下面有多少状态


}
