package com.cdut.sx.dao.impl;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.friendsdao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.friends;
import com.cdut.sx.pojo.user;
import com.cdut.sx.utils.HibernateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository("friendsdao")
public class friendsdaoImp implements friendsdao {
    @Resource
    private Userdao userdao;

    @Override
    public ArrayList<friends> queryMyFriends(String me) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        ArrayList<friends> myFriends = (ArrayList<friends>) session.createQuery("from friends where friendsFrom=:friendfrom").setString("friendfrom", me).list();
        trans.commit();
        return myFriends;
    }

    @Override
    public void save(String friend1, String friend2) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        friends friend = new friends();
        friend.setFriendsFrom(friend1);
        friend.setFriendsTo(friend2);
        session.save(friend);
        trans.commit();
    }

    @Override
    public void update(String friend1, String friends2, boolean isCancel) {

    }

    @Override
    public int findCount() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "select friendsId from friends";
        Query query = session.createQuery(hql);
        List list = query.list();
        trans.commit();

        if (list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public PageBean<user> findByPage(Integer currPage) {
        // TODO Auto-generated method stub


        //测试
        System.out.println("进入findByPage");
        Session session = HibernateUtil.getSession();
        Transaction trans = null;
        int totalCount = 0;
        int pagesize = 0;
        PageBean<user> pageBean = new PageBean<user>();

        try {
            trans = session.beginTransaction();

            // 封装当前页数
            pageBean.setCurrPage(currPage);
            // 封装每页显示的记录数
            pagesize = 10;
            pageBean.setPageSize(pagesize);


            //测试
            System.out.println("马上执行findCount");
            // 封装总记录数
            totalCount = this.findCount();


            //测试
            System.out.println(totalCount);
            pageBean.setTotalCount(totalCount);
            trans.commit();

        } catch (Exception e) {

        }
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<friends> list = this.findByPage(begin, pagesize);
        if (list.isEmpty()) return null;
        List<user> inforList = new ArrayList<>();
        for (friends one : list) {
            List<user> friend = userdao.queryByName(one.getFriendsTo());//查询好友信息
            if (friend != null && !friend.isEmpty())
                inforList.add(friend.get(0));//将好友信息
        }
        pageBean.setList(inforList);
        return pageBean;
    }

    @Override
    public List<friends> findByPage(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(friends.class);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数

        List<friends> list = criteria.list();

        trans.commit();
        return list;
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String friends1, String friend2) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List OriFriend = session.createQuery("from friends where friendsFrom=:friendfrom and friendsTo=:friendto").setString("friendfrom", friends1).setString("friendto", friend2).list();
        friends friendsTodelete = (friends) OriFriend.get(0);
        if (friendsTodelete != null)
            session.delete(friendsTodelete);
        trans.commit();
    }
}

