package com.cdut.sx.controller;

import com.cdut.sx.pojo.friends;
import com.cdut.sx.service.friendsdaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller

public class friendsController {
    @Autowired
    private friends friends;
    @Autowired
    private friendsdaoImp dao;
    private Integer currPage = 1;

    @RequestMapping("/makeFriend")
    public ModelAndView makeFriend(HttpSession session, HttpServletRequest request) {
        String username = (String) session.getAttribute("name");
        String friendsTo = request.getParameter("friendsTo");
        //  friends.setFriendsTo(friendsTo);
        if (username != friendsTo)//不和自己交朋友
        {
            friends newFriend = new friends();
            newFriend.setFriendsTo(friendsTo);
            newFriend.setFriendsFrom(username);
            dao.save(newFriend);
        }
        return new ModelAndView("views/friends");
    }
    @RequestMapping("/friendList")
    public ModelAndView friendList(HttpSession session) {
        String username = (String) session.getAttribute("name");
        ModelAndView mav = new ModelAndView("views/friends");
        if (currPage == 0)
            currPage = 1;
        ArrayList<friends> friendList = dao.queryMyFriends(username);
        if (friendList != null && friendList.size() != 0) {
            mav.addObject("friendsList", friendList);
            return mav;
        } else {
            ModelAndView errormav = new ModelAndView("views/error");
            return errormav;
        }
    }

}

