package com.cdut.sx.controller;

import com.cdut.sx.pojo.Friends;
import com.cdut.sx.pojo.PageBean;
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
    @Autowired
    private UserService userdao;


    private Integer currPage = 1;

    static ModelAndView getModelAndView(String username, ModelAndView mav, FriendsService dao) {
        ArrayList<Friends> friendList = dao.queryMyFriends(username);
        if (friendList != null && !friendList.isEmpty()) {
            mav.addObject("friendsList", friendList);
            return mav;
        } else {
            mav.addObject("info", "您暂时还未结交好友");
            return mav;
        }
    }

    @RequestMapping("/makeFriend")
    public ModelAndView makeFriend(HttpSession session, @RequestParam(value = "friendsTo")
            String friendsTo) {
        String username = (String) session.getAttribute("name");
        ArrayList<Friends> friends = dao.queryMyFriends(username);
        for (Friends friend : friends) {
            if (friend.getFriendsTo().equals(friendsTo)) return new ModelAndView("forward:/friendList");
        }
        if (!username.equals(friendsTo))//不和自己交朋友
        {
            Friends newFriend = new Friends();
            newFriend.setFriendsTo(friendsTo);
            newFriend.setFriendsFrom(username);
            dao.save(newFriend);
        }
        return new ModelAndView("forward:/friendList");
    }

    @RequestMapping("/friendList")
    public ModelAndView friendList(HttpSession session) {
        String username = (String) session.getAttribute("name");
        ModelAndView mav = new ModelAndView("views/friends");
        ArrayList<User> users = (ArrayList<User>) userdao.findAllUsers();
        mav.addObject("users", users);
        if (currPage == 0)
            currPage = 1;
        return getModelAndView(username, mav, dao);
    }

    @RequestMapping(value = "/tochat/{friendsName}")
    public ModelAndView toChatWithFriends(@PathVariable("friendsName") String friendsName, HttpSession session) {
        session.setAttribute("otherUserName", friendsName);
        return new ModelAndView("views/contact");
    }

    /**
     * 查找朋友
     */
    @RequestMapping("/findFriends")
    public ModelAndView findArea(HttpServletRequest request, HttpSession session, @RequestParam(name = "currPage", required = false) Integer Currpage) {
        ModelAndView mav = new ModelAndView("views/friends");
        if (Currpage != null) currPage = Currpage;
        if (currPage == 0)
            currPage = 1;
        PageBean<Friends> allUsers = dao.findFriendsByPage(currPage);
        if (allUsers != null){
            mav.addObject("allUsers", allUsers); }
        PageBean<Friends> myFriends = dao.findMyFriendsByPage(currPage, (String) session.getAttribute("name"));
        if(myFriends != null){
            mav.addObject("myFriends",myFriends);
        }
        return mav;
    }
}

