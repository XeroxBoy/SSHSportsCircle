package com.cdut.sx.dao.impl;

import com.cdut.sx.dao.reminddao;
import com.cdut.sx.pojo.remind;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Transactional
@Service
public class reminddaoImp {
    @Autowired
    private reminddao reminddao;

    public ArrayList<remind> queryAll(String user) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<remind> myReminds = (ArrayList<remind>) session.createQuery("from remind where userTo=:userto").setString("userto", user).list();
        trans.commit();
        return myReminds;
    }

    public Object save(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.save(remind);
        trans.commit();
        return null;
    }

    public void update(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.update(remind);
        trans.commit();
    }

    public void delete(remind remind) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.delete(remind);
        trans.commit();
    }
}

