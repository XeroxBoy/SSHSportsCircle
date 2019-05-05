package com.cdut.sx.service;


import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserService {
    @Autowired
    private Userdao userdao;

    //找到所有的用户
    public List<User> findAllUsers() {
        return userdao.findAll();
    }

    //找到我的圈子
    public Set<Circle> queryMyCircle(String username) {
        List<User> user = userdao.queryByName(username);
        Set<Circle> circles = user.get(0).getCircles();
        return circles;
    }

    // 通过名字找到user
    public List<User> queryByName(String username) {
        List<User> user = userdao.queryByName(username);
        return user;
    }

    public void save(User user) {
        userdao.save(user);
    }

    public void update(User user) {
        userdao.save(user);
    }

    public void delete(User user) {
        userdao.delete(user);
    }


}
