package com.cdut.sx.service;

import com.alibaba.fastjson.JSONObject;
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

@Service
@Transactional
public class MessageService {
    @Resource
    Userdao userdao;
    @Autowired
    Messagedao messagedao;
    @Autowired
    CircleService circledao;

    private static boolean isChanged = false;
    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisService;
    // 查询所有message
    public ArrayList<Message> queryAll() {
        // TODO Auto-generated method stub
        return (ArrayList<Message>) messagedao.findAll();
    }

    //改变ischanged状态
    public void change() {
        isChanged = true;
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
        int pagesize = 5;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = (int) this.findCount();
        pageBean.setTotalCount(totalCount);
        trans.commit();
        Criteria criteria = session.createCriteria(Message.class);
        int begin = (currPage - 1) * pagesize;
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        List<Message> list;
        String currpage = String.valueOf(currPage);
//        if (redisService.get(currpage) == null || isChanged) {
            list = criteria.list();
//            redisService.set(String.valueOf(currPage), JSONObject.toJSONString(list));
//        } else {
//            String jsonList = redisService.get(currpage);
//            list = JSONObject.parseArray(jsonList, Message.class);
//        }
        pageBean.setList(list);
        return pageBean;
    }

    public List<Message> findByPage(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Message.class);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Message> list;
//        if (redisService.get("1") == null || isChanged) {
            list = criteria.list();
//            redisService.set("1", JSONObject.toJSONString(list));
//        } else {
//            String jsonList = redisService.get("1");
//            list = JSONObject.parseArray(jsonList, Message.class);
//        }
        trans.commit();
        session.close();
        return list;
        // TODO Auto-generated method stub

    }

    public long findCount() {
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

    public PageBean<Message> findByPage(int currPage, String userId, String area) {//找出这个人发过的状态
        PageBean<Message> pageBean = new PageBean<Message>();
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        int pagesize = 5;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        long totalCount = this.findAreaCount(area);
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        List<Message> list;
        Criteria criteria = session.createCriteria(Message.class);
        Criterion criterion = Expression.eq("userId", userId);

        criteria.add(criterion);
        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
//        if (redisService.get(userId) == null || isChanged) {
            list = criteria.list();
//            redisService.set(userId, JSONObject.toJSONString(list));
//        } else {
//            String jsonList = redisService.get(userId);
//            list = JSONObject.parseArray(jsonList, Message.class);
//        }
        pageBean.setList(list);
        trans.commit();
        session.close();
        return pageBean;
    }

    public List<Message> findByPageArea(int begin, int pagesize, String area) {//跟据页数和圈子筛选
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

    //根据圈子筛选出帖子信息并显示
    public PageBean<Message> findByArea(Integer currPage, String area) {
        PageBean<Message> pageBean = new PageBean<Message>();
        List<Message> list;
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 5;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = (int) this.findAreaCount(area);
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        Double num = Math.ceil((double) totalCount / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        String currpage = String.valueOf(currPage);
//        if (redisService.get(currpage) == null || isChanged) {
        list = this.findByPageArea(begin, pagesize, area);
//            redisService.set(String.valueOf(currPage), JSONObject.toJSONString(list));
//        } else {
//            String jsonList = redisService.get(currpage);
//            list = JSONObject.parseArray(jsonList, Message.class);
//        }
        if(list != null && list.size()!=0)
        pageBean.setList(list);
        if(list.size() == 0)
            pageBean.setList(new ArrayList<>());
        return pageBean;
    }

}
