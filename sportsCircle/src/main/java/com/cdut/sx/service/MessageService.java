package com.cdut.sx.service;

import com.cdut.sx.dao.Messagedao;
import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.Message;
import com.cdut.sx.pojo.PageBean;
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
import java.util.logging.Logger;

@Service
@Transactional
public class MessageService {
    @Resource
    Userdao userdao;
    @Autowired
    Messagedao messagedao;
    @Autowired
    CircleService circledao;
    // 查询所有message
    public ArrayList<Message> queryAll() {
        // TODO Auto-generated method stub
        return (ArrayList<Message>) messagedao.findAll();
    }

    // 按照username查询message 即这个人发过的状态
    public List<Message> queryByUserId(String username) {
        // TODO Auto-generated method stub
        return messagedao.findByUser(username);
    }

    public Message queryById(int messageId) { //按message编号查message
        // TODO Auto-generated method stub
        Optional<Message> messages = messagedao.findById(messageId);
        return messages.orElse(null);
    }

    public void save(Message message) {
        // TODO Auto-generated method stub
        messagedao.save(message);
    }

    public void update(Message message) {
        // TODO Auto-generated method stub
        messagedao.save(message);

    }


    public void delete(Message message) {
        // TODO Auto-generated method stub
        messagedao.delete(message);
    }

    /*
     * 1.每一页有1个记录
     *
     */
    public PageBean<Message> findByPage(Integer currPage) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        PageBean<Message> pageBean = new PageBean<Message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 1;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = (int) this.findCount();
        pageBean.setTotalCount(totalCount);
        trans.commit();
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<Message> list = this.findByPage(begin, pagesize);
        pageBean.setList(list);
        return pageBean;
    }

    public List<Message> findByPage(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Message> list = criteria.list();
        trans.commit();
        session.close();
        return list;
        // TODO Auto-generated method stub

    }

    public long findCount() {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        long sizes = messagedao.count();
        trans.commit();
        session.close();
        if (sizes > 0) {
            return sizes;
        }
        return 0;
    }

    public PageBean<Message> findMineByPage(Integer currPage, String userId) {
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        PageBean<Message> pageBean = new PageBean<Message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 1;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = (int) this.findMyCount(userId);
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<Message> list = this.findByPage(begin, pagesize, userId);
        pageBean.setList(list);
        trans.commit();
        session.close();
        return pageBean;

    }


    public long findMyCount(String userId) {//看我发了多少条记录
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        long sizes = messagedao.findMyCount(userId);
        trans.commit();
        session.close();
        if (sizes > 0) {
            return sizes;
        }
        return 0;
    }

    public long findAreaCount(String area)//看每个圈子下面有多少状态
    {
        List<Circle> thisCircle = circledao.findCircle(area);
        if (thisCircle.isEmpty()) return 0;
        long sizes = thisCircle.get(0).getMessageCount();

        if (sizes > 0) {
            return sizes;
        }
        return 0;
    }

    public List<Message> findByPage(int begin, int pagesize, String userId) {//找出这个人发过的状态
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        Criterion criterion = Expression.eq("userId", userId);
        criteria.add(criterion);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Message> list = criteria.list();
        for (Message i : list) Logger.getLogger("abc").info(i.getContent());
        trans.commit();
        session.close();
        return list;

    }

    public List<Message> findByPageArea(int begin, int pagesize, String area) {//跟据页数和圈子筛选
        // TODO Auto-generated method stub
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        List<Circle> circle = circledao.findCircle(area);
        Criterion criterion = Expression.eq("belongTo.circleId", circle.get(0).getCircleId());
        Criterion criterion1 = Expression.eq("active", "active");
        criteria.add(criterion);
        criteria.add(criterion1);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Message> list = criteria.list();
        trans.commit();
        session.close();
        return list;

    }

    public PageBean<Message> findByArea(Integer currPage, String area) {
        // TODO 自动生成的方法存根

        PageBean<Message> pageBean = new PageBean<Message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 2;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = (int) this.findAreaCount(area);
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        List<Message> list = this.findByPageArea(begin, pagesize, area);
        pageBean.setList(list);

        return pageBean;
    }

}

