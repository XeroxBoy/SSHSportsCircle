package com.cdut.sx.dao;


import com.cdut.sx.pojo.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Userdao extends JpaRepository<user, Integer> {
    @Query(value = "select u from user u where u.username=?1")
    public List<user> queryByName(String username);//根据ID查用户
//	public List<user> queryByCode(String code);//根据激活码查用户
}
