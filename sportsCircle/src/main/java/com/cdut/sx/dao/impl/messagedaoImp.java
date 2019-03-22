package com.cdut.sx.dao.impl;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.messagedao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.message;
import com.cdut.sx.utils.HibernateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository("messagedao")
public class messagedaoImp implements messagedao {
    @Resource
    Userdao userdao;

    // 查询所有message
    @Override
    public ArrayList<message> queryAll() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<message> messages = (ArrayList<message>) session.createQuery(
                "from message and active='active'").list();
        trans.commit();

        return messages;
    }

    // 按照username查询message 即这个人发过的状态
    @Override
    public List<message> queryByUserId(String username) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List<message> message = session
                .createQuery("from message where userId=:tusername and active='active'")
                .setString("tusername", username).list();
        trans.commit();

        return message;
    }

    @Override
    public List<message> queryById(int messageId) { //按message编号查message
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List<message> message = session
                .createQuery("from message where messageid=:tmessagename and active='active'")
                .setLong("tmessagename", messageId).list();
        trans.commit();

        return message;
    }

    @Override
    public void save(message message) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();

        session.save(message);
        trans.commit();

    }

    @Override
    public void update(message message) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();

        session.update(message);
        trans.commit();

    }

    @Override
    public void delete(message message) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();

        session.delete(message);
        trans.commit();

    }

    /*
     * 1.每一页有1个记录
     *
     */
    @Override
    public PageBean<message> findByPage(Integer currPage) {
        // TODO Auto-generated method stub


        //测试
        System.out.println("进入findByPage");
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        PageBean<message> pageBean = new PageBean<message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 1;
        pageBean.setPageSize(pagesize);


        //测试
        System.out.println("马上执行findCount");
        // 封装总记录数
        int totalCount = this.findCount();


        //测试
        System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        trans.commit();
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<message> list = this.findByPage(begin, pagesize);
        pageBean.setList(list);
        return pageBean;
    }

    public List<message> findByPage(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(message.class);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数

        List<message> list = criteria.list();

        trans.commit();
        return list;
        // TODO Auto-generated method stub

    }

    public int findCount() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "select messageid from message where active='active'";
        Query query = session.createQuery(hql);
        List list = query.list();
        trans.commit();

        if (list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public PageBean<message> findMineByPage(Integer currPage, String userId) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        PageBean<message> pageBean = new PageBean<message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 1;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = this.findMyCount(userId);
        pageBean.setTotalCount(totalCount);
        trans.commit();
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<message> list = this.findByPage(begin, pagesize, userId);
        pageBean.setList(list);
        return pageBean;

    }

    public int findMyCount(String userId) {//看我发了多少条记录
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "select messageid from message where userId=:userId and active='active'";
        System.out.println("进入了findMyCount，马上进行查询");
        Query query = session.createQuery(hql).setString("userId", userId);
        List list = query.list();
        if (list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public int findAreaCount(String area)//看每个圈子下面有多少状态
    {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "select messageid from message where belongTo=:area and active=:active";
        Query query = session.createQuery(hql).setString("area", area).setString("active", "active");
        List list = query.list();
        if (list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public List<message> findByPage(int begin, int pagesize, String userId) {//找出这个人发过的状态
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(message.class);
        Criterion criterion = Expression.eq("userId", userId);
        criteria.add(criterion);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<message> list = criteria.list();
        trans.commit();
        return list;

    }

    public List<message> findByPageArea(int begin, int pagesize, String area) {//跟据页数和圈子筛选
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(message.class);
        Criterion criterion = Expression.eq("belongTo", area);
        Criterion criterion1 = Expression.eq("active", "active");
        criteria.add(criterion);
        criteria.add(criterion1);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<message> list = criteria.list();
        trans.commit();
        return list;

    }

    @Override
    public PageBean<message> findByArea(Integer currPage, String area) {
        // TODO 自动生成的方法存根
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        PageBean<message> pageBean = new PageBean<message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 2;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = this.findAreaCount(area);
        //  System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        trans.commit();
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<message> list = this.findByPageArea(begin, pagesize, area);
        pageBean.setList(list);
        return pageBean;
    }

}

