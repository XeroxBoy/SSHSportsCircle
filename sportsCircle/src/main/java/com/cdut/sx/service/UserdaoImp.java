package com.cdut.sx.service;

/*
import utils.MailUtils;
*/

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserdaoImp {
    @Autowired
    private Userdao userdao;

    public ArrayList<user> queryAll() {
        // TODO Auto-generated method stub


        ArrayList<user> users = (ArrayList<user>) userdao.findAll();

        return users;
    }

    public List<user> queryByName(String username) {
        // TODO Auto-generated method stub
        List<user> user = userdao.queryByName(username);
        return user;
    }

    public void save(user user) {
        // TODO Auto-generated method stub
        userdao.save(user);
    }

    public void update(user user) {
        // TODO Auto-generated method stub
        userdao.save(user);
    }

    public void delete(user user) {
        // TODO Auto-generated method stub
        userdao.delete(user);
    }


}
