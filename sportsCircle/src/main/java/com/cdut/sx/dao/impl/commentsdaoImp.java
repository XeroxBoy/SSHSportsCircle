package com.cdut.sx.dao.impl;

import com.cdut.sx.dao.Commentsdao;
import com.cdut.sx.dao.messagedao;
import com.cdut.sx.pojo.comments;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository("Commentsdao")
public class commentsdaoImp implements Commentsdao {
    @Resource
    private messagedao messagedao;

    //  private Commentsdao commentdao=new commentsdaoImp();
    @Override
    public ArrayList<comments> queryAll() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<comments> comments = (ArrayList<comments>) session.createQuery("from comments where active=:active").setString("active", "active").list();
        trans.commit();

        return comments;
    }

    @Override
    public List queryByMsgId(String messagename) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List comments = session.createQuery("from comments where messagebelongTo=:tcommentsname and active=:active").setString("active", "active").setString("tcommentsname", messagename).list();
        trans.commit();

        return comments;
    }

    @Override
    public void save(comments comments) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.save(comments);
        trans.commit();

    }

    @Override
    public void update(comments comments) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        session.update(comments);
        trans.commit();

    }

    @Override
    public void delete(comments comments) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();

        session.delete(comments);
        trans.commit();

    }

    @Override
    public List<comments> queryById(int commentId) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List<comments> comments = session.createQuery("from comments where commentId=:tcommentsid and active=:active").setString("active", "active").setLong("tcommentsid", commentId).list();
        trans.commit();

        return comments;
    }

}
