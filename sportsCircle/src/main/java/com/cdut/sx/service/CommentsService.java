package com.cdut.sx.service;

import com.cdut.sx.dao.Commentsdao;
import com.cdut.sx.dao.Messagedao;
import com.cdut.sx.pojo.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CommentsService {
    @Resource
    private Messagedao messagedao;
    @Autowired
    private Commentsdao commentdao;

    public ArrayList<Comments> queryAll() {
        // TODO Auto-generated method stub
        ArrayList<Comments> comments = (ArrayList<Comments>) commentdao.queryAll();
        return comments;
    }

    public List queryByMsgId(String messagename) {
        // TODO Auto-generated method stub
        List comments = commentdao.queryByMsgId(messagename);
        return comments;
    }

    public void save(Comments comments) {
        commentdao.save(comments);
    }

    public void update(Comments comments) {
        // TODO Auto-generated method stub
        commentdao.save(comments);
    }


    public void delete(Comments comments) {
        // TODO Auto-generated method stub
        commentdao.delete(comments.getCommentId());
    }

    public List<Comments> queryById(int commentId) {
        // TODO Auto-generated method stub
        return commentdao.queryById(commentId);
    }

}
