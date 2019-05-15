package com.cdut.sx.service;

import com.cdut.sx.dao.Friendsdao;
import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.Friends;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.User;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendsService {
    @Resource
    private Userdao userdao;
    @Autowired
    private Friendsdao friendsdao;

    public ArrayList<Friends> queryMyFriends(String me) {

        return friendsdao.queryMyFriends(me);
    }

    public void save(Friends friend) {
        friendsdao.save(friend);
    }

    public void update(String friend1, String friends2, boolean isCancel) {
        Friends friend = new Friends();
        friend.setFriendsFrom(friend1);
        friend.setFriendsTo(friends2);
        if (isCancel)
            friendsdao.delete(friend1, friends2);
        else
            friendsdao.save(friend);
    }

    public int findCount() {
        return (int) friendsdao.count();
    }

    public PageBean<User> findByPage(Integer currPage) throws Exception {
        int totalCount = 0;
        int pagesize = 0;
        PageBean<User> pageBean = new PageBean<User>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        pagesize = 10;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        totalCount = this.findCount();
        pageBean.setTotalCount(totalCount);

        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<Friends> list = this.findByPage(begin, pagesize);
        if (list.isEmpty()) return null;
        List<User> inforList = new ArrayList<>();
        for (Friends one : list) {
            List<User> friend = userdao.queryByName(one.getFriendsTo());//查询好友信息
            if (friend != null && !friend.isEmpty())
                inforList.add(friend.get(0));//将好友信息
        }
        pageBean.setList(inforList);
        return pageBean;
    }

    public List<Friends> findByPage(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Friends.class);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Friends> list = criteria.list();
        trans.commit();
        return list;
    }
    public List<Friends> findMineByPage(int begin, int pagesize, String username) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Friends.class);
        Criterion criterion = Expression.eq("friendsFrom", username);
        criteria.add(criterion);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Friends> list = criteria.list();
        trans.commit();
        return list;
    }

    public void delete(String friends1, String friend2) {
        friendsdao.delete(friends1, friend2);
    }

    public PageBean<Friends> findFriendsByPage(Integer currPage) {
        PageBean<Friends> pageBean = new PageBean<Friends>();
        List<Friends> list;
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 3;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = friendsdao.findAll().size();
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        String currpage = String.valueOf(currPage);
        list = this.findByPage(begin, pagesize);
        if(list != null && list.size()!=0)
            pageBean.setList(list);
        if(list.size() == 0)
            pageBean.setList(new ArrayList<>());
        return pageBean;
    }

    public PageBean<Friends> findMyFriendsByPage(Integer currPage,String username) {
        PageBean<Friends> pageBean = new PageBean<Friends>();
        List<Friends> list;
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 3;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = friendsdao.queryMyFriends(username).size();
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        Double num = Math.ceil((double) totalCount / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        list = this.findMineByPage(begin, pagesize, username);
        if(list != null && list.size()!=0)
            pageBean.setList(list);
        if(list.size() == 0)
            pageBean.setList(new ArrayList<>());
        return pageBean;
    }

}

