package com.cdut.sx.service;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.messagedao;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.message;
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
import java.util.Optional;

@Service
@Transactional
public class messagedaoImp {
    @Resource
    Userdao userdao;
    @Autowired
    messagedao messagedao;

    // 查询所有message
    public ArrayList<message> queryAll() {
        // TODO Auto-generated method stub
        return (ArrayList<message>) messagedao.findAll();
    }

    // 按照username查询message 即这个人发过的状态
    public List<message> queryByUserId(String username) {
        // TODO Auto-generated method stub
        return messagedao.findByUser(username);
    }

    public message queryById(int messageId) { //按message编号查message
        // TODO Auto-generated method stub
        Optional<message> messages = messagedao.findById(messageId);
        if (messages.get() == null) return null;
        return messages.get();
    }

    public void save(message message) {
        // TODO Auto-generated method stub
        messagedao.save(message);
    }

    public void update(message message) {
        // TODO Auto-generated method stub
        messagedao.save(message);

    }


    public void delete(message message) {
        // TODO Auto-generated method stub
        messagedao.delete(message);
    }

    /*
     * 1.每一页有1个记录
     *
     */
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
        int totalCount = (int) this.findCount();
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

    public long findCount() {
        // TODO Auto-generated method stub
        long sizes = messagedao.count();

        if (sizes > 0) {
            return sizes;
        }
        return 0;
    }

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
        int totalCount = (int) this.findMyCount(userId);
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


    public long findMyCount(String userId) {//看我发了多少条记录
        // TODO Auto-generated method stub
        long sizes = messagedao.findMyCount(userId);
        if (sizes > 0) {
            return sizes;
        }
        return 0;
    }

    public long findAreaCount(String area)//看每个圈子下面有多少状态
    {
        long sizes = messagedao.findAreaCount(area);
        if (sizes > 0) {
            return sizes;
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
        int totalCount = (int) this.findAreaCount(area);
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

