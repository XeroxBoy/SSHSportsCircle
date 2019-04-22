package com.cdut.sx.controller;

import com.cdut.sx.pojo.Friends;
import com.cdut.sx.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WebSocketController {
    @Autowired
    private Friends friends;
    @Autowired
    private FriendsService dao;
    @RequestMapping("/toContact")
    public ModelAndView contact(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/contact");
        String username = (String) session.getAttribute("name");
        return FriendsController.getModelAndView(username, mav, dao);
    }


}

