package com.cdut.sx.service;


import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserService {
    @Autowired
    private Userdao userdao;

    public ArrayList<User> queryAll() {
        // TODO Auto-generated method stub
        ArrayList<User> users = (ArrayList<User>) userdao.findAll();
        return users;
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
