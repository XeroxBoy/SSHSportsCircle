package com.cdut.sx.controller;

import com.cdut.sx.dao.friendsdao;
import com.cdut.sx.dao.impl.friendsdaoImp;
import com.cdut.sx.pojo.friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;
@Controller

public class friendsController {
    @Autowired
    private friends friends;
    // private Userdao userdao=new UserdaoImp();
    @Autowired
    private friendsdao dao ;
    private Integer currPage = 1;
    public String makeFriend(){
        Map<String, Object> session = ActionContext.getContext().getSession();//将用户信息存入session
        String username = (String) session.get("name");
        HttpServletRequest request = ServletActionContext.getRequest();
        String friendsTo = request.getParameter("friendsTo");

        //  friends.setFriendsTo(friendsTo);
        if (username != friendsTo)//不和自己交朋友
            dao.save(username, friendsTo);
        return "makeFriends";

    }

    public String friendList() {
        Map<String, Object> session = ActionContext.getContext().getSession();//将用户信息存入session
        String username = (String) session.get("name");
        ValueStack stack = ActionContext.getContext().getValueStack();
        if (currPage == 0)
            currPage = 1;
        ArrayList<friends> friendList = dao.queryMyFriends(username);
        if (friendList != null && friendList.size() != 0)
            stack.set("friends", friendList);
        else {
            return "nofriends";
        }
        for (friends fri : friendList) {
            System.out.println("朋友" + fri.getFriendsTo());
        }
        return "showList";
    }

    @Override
    public friends getModel() {
        if (friends== null) {
            friends = new friends();
        }
        return friends;
    }
}

