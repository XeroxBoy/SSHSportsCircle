package com.cdut.sx.dao.impl;

/*
import utils.MailUtils;
*/

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.user;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("Userdao")
public class UserdaoImp implements Userdao {

    @Override
    public ArrayList<user> queryAll() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<user> users = (ArrayList<user>) session.createQuery("from user").list();
        trans.commit();
        return users;
    }

    @Override
    public List queryByName(String username) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();

        List user = ((SQLQuery) session.createSQLQuery("select * from user where userName=?").setString(0, username)).addEntity(user.class).list();

        trans.commit();

        return user;
    }

    @Override
    public void save(user user) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.save(user);
        trans.commit();

    }

    @Override
    public void update(user user) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.update(user);
        trans.commit();

    }

    @Override
    public void delete(user user) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.delete(user);
        trans.commit();

    }


}
