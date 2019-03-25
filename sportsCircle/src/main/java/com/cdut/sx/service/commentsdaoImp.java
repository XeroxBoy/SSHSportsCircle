package com.cdut.sx.service;

import com.cdut.sx.dao.Commentsdao;
import com.cdut.sx.dao.messagedao;
import com.cdut.sx.pojo.comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class commentsdaoImp {
    @Resource
    private messagedao messagedao;
    @Autowired
    private Commentsdao commentdao;

    //    @Autowired
//    @Qualifier("entityManagerFactory")
//    EntityManagerFactory factory;
//    @PersistenceContext
//    private EntityManager manager;
    public ArrayList<comments> queryAll() {
        // TODO Auto-generated method stub
        return commentdao.queryAll();
    }

    public List queryByMsgId(String messagename) {
        // TODO Auto-generated method stub
        List comments = commentdao.queryByMsgId(messagename);
        return comments;
    }

    public void save(comments comments) {
        commentdao.save(comments);
    }

    public void update(comments comments) {
        // TODO Auto-generated method stub
        commentdao.save(comments);
    }


    public void delete(comments comments) {
        // TODO Auto-generated method stub
        commentdao.delete(comments.getCommentId());
    }

    public List<comments> queryById(int commentId) {
        // TODO Auto-generated method stub
        return commentdao.queryById(commentId);
    }

}
//        // 1.获得Factory
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA");
//        // 2.获取Manager
//        EntityManager manager = factory.createEntityManager();
//        // 3.获得事务，并开启事务
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        // 4.执行sql
//        comments commentsBase=manager.find(comments.class,comments.getCommentId());
//        manager.remove(commentsBase);
//        // 5.提交事务，关闭资源
//        transaction.commit();
//        manager.close();
//        factory.close();