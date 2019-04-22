package com.cdut.sx.controller;

import com.cdut.sx.pojo.Friends;
import com.cdut.sx.pojo.User;
import com.cdut.sx.service.FriendsService;
import com.cdut.sx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller

public class FriendsController {
    @Autowired
    private Friends friends;
    @Autowired
    private FriendsService dao;
    private Integer currPage = 1;

    static ModelAndView getModelAndView(String username, ModelAndView mav, FriendsService dao) {
        ArrayList<Friends> friendList = dao.queryMyFriends(username);
        UserService userdao = new UserService();
        ArrayList<User> users = userdao.queryAll();
        if (friendList != null && !friendList.isEmpty()) {
            mav.addObject("friendsList", friendList);
            mav.addObject("usersList", users);
            return mav;
        } else {
            mav.addObject("info", "您暂时还未结交好友");
            return mav;
        }
    }

    @RequestMapping("/makeFriend")
    public ModelAndView makeFriend(HttpSession session, HttpServletRequest request, @RequestParam(value = "friendsTo")
            String friendsTo) {
        String username = (String) session.getAttribute("name");
        if (!username.equals(friendsTo))//不和自己交朋友
        {
            Friends newFriend = new Friends();
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
        return getModelAndView(username, mav, dao);
    }

    @RequestMapping(value = "/tochat/{friendsName}")
    public ModelAndView toChatWithFriends(@PathVariable("friendsName") String friendsName, HttpSession session) {
        session.setAttribute("otherUserName", friendsName);
        return new ModelAndView("views/contact");
    }

}

