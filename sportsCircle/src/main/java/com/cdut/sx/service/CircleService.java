package com.cdut.sx.service;

import com.cdut.sx.dao.Circledao;
import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CircleService {
    @Autowired
    private Circledao circledao;
    public List<Circle> search(String key) {
        List<Circle> circles = circledao.search(key);
        return circles;
    }
    public void save(Circle message) {
        circledao.save(message);
    }

    public void update(Circle message) {
        circledao.save(message);
    }

    public void delete(Circle message) {
        circledao.delete(message);
    }

    public List<Circle> findCircle(String name) {
        List<Circle> circle;
        circle = circledao.findByCircleName(name);
        return circle;
    }

    public List<Circle> findAllCircles() {
        return circledao.findAll();
    }

    public PageBean<Circle> findCirclesByPage(Integer currPage) {
        PageBean<Circle> pageBean = new PageBean<Circle>();
        List<Circle> list;
        // 封装当前页数
        pageBean.setCurrPage(currPage);
        // 封装每页显示的记录数
        int pagesize = 15;
        pageBean.setPageSize(pagesize);
        // 封装总记录数
        int totalCount = this.findAllCircles().size();
        pageBean.setTotalCount(totalCount);
        // 封装总页数
        Double num = Math.ceil((double) totalCount / pagesize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pagesize;
        String currpage = String.valueOf(currPage);
        list = this.findByPageCircle(begin, pagesize);
        if(list != null && list.size()!=0)
            pageBean.setList(list);
        if(list.size() == 0)
            pageBean.setList(new ArrayList<>());
        return pageBean;
    }

    public List<Circle> findByPageCircle(int begin, int pagesize) {
        Session session = HibernateUtil.getSession();
        Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(Circle.class);

        criteria.setFirstResult(begin);// 从这条记录开始
        criteria.setMaxResults(pagesize);// 最大记录数
        List<Circle> list = criteria.list();
        trans.commit();
        session.close();
        return list;
    }
}

