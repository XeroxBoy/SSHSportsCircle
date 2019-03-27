package com.cdut.sx.controller;

import com.cdut.sx.pojo.Remind;
import com.cdut.sx.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class RemindController {
    @Autowired
    private Remind remind;
    @Autowired
    private RemindService dao;

    @RequestMapping("/sendPromise")
    public String sendPromise() {//邀约函数
        sendRemind(remind.getUserTo());//给别人发送应约消息
        return "views/fitcircle";
    }
    @RequestMapping("/makePromise")
    public String makePromise() {//应约函数
        remind.setActive("dead");
        dao.save(remind);//保存dead状态到数据库
        sendRemind(remind.getUserFrom());//给别人发送应约消息
        return "views/fitcircle";
    }

    private void sendRemind(String userTo) { //发送消息的函数
    }
}

