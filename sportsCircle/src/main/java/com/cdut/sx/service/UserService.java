package com.cdut.sx.service;


import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
    @Autowired
    private Userdao userdao;

    public List<User> findAllUsers() {
        // TODO Auto-generated method stub
        return userdao.findAll();
    }

    public List<Circle> queryMyCircle(String username) {
        List<User> user = userdao.queryByName(username);
        List<Circle> circles = user.get(0).getCircles();
        return circles;
    }

    public List<User> queryByName(String username) {
        // TODO Auto-generated method stub
        List<User> user = userdao.queryByName(username);
        return user;
    }

    public void save(User user) {
        // TODO Auto-generated method stub
        userdao.save(user);
    }

    public void update(User user) {
        // TODO Auto-generated method stub
        userdao.save(user);
    }

    public void delete(User user) {
        // TODO Auto-generated method stub
        userdao.delete(user);
    }


}
