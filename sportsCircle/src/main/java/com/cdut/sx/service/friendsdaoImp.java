package com.cdut.sx.service;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.friendsdao;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.friends;
import com.cdut.sx.pojo.user;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class friendsdaoImp {
    @Resource
    private Userdao userdao;
    @Autowired
    private friendsdao friendsdao;

    public ArrayList<friends> queryMyFriends(String me) {

        return friendsdao.queryMyFriends(me);
    }

    public void save(friends friend) {
        // TODO Auto-generated method stub
        friendsdao.save(friend);
    }

    public void update(String friend1, String friends2, boolean isCancel) {
        friends friend = new friends();
        friend.setFriendsFrom(friend1);
        friend.setFriendsTo(friends2);
        if (isCancel)
            friendsdao.delete(friend1, friends2);
        else
            friendsdao.save(friend);
    }

    public int findCount() {
        // TODO Auto-generated method stub
        return (int) friendsdao.count();
    }

    public PageBean<user> findByPage(Integer currPage) {
        // TODO Auto-generated method stub


        //测试
        System.out.println("进入findByPage");

        int totalCount = 0;
        int pagesize = 0;
        PageBean<user> pageBean = new PageBean<user>();
        try {
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

    public void delete(String friends1, String friend2) {
        friendsdao.delete(friends1, friend2);
    }
}

