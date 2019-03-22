package com.cdut.sx.dao.impl;

import com.cdut.sx.dao.reminddao;
import com.cdut.sx.pojo.remind;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository("reminddao")
public class reminddaoImp implements reminddao {


    @Override
    public ArrayList<remind> queryAll(String user) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<remind> myReminds = (ArrayList<remind>) session.createQuery("from remind where userTo=:userto").setString("userto", user).list();
        trans.commit();
        return myReminds;
    }

    @Override
    public void save(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.save(remind);
        trans.commit();
    }

    @Override
    public void update(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.update(remind);
        trans.commit();
    }

    @Override
    public void delete(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.delete(remind);
        trans.commit();
    }
}

