package com.cdut.sx.controller;

import com.cdut.sx.dao.impl.reminddaoImp;
import com.cdut.sx.dao.reminddao;
import com.cdut.sx.pojo.remind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class remindController {
    @Autowired
    private remind remind;
    @Autowired
    private reminddao dao ;

    @Override
    public remind getModel() {
        if (remind == null)
            return new remind();
        return remind;
    }

    public String sendPromise() {//邀约函数

        sendRemind(remind.getUserTo());//给别人发送应约消息
        return "sendPromise";
    }

    public String makePromise() {//应约函数
        remind.setActive("dead");
        dao.save(remind);//保存dead状态到数据库
        sendRemind(remind.getUserFrom());//给别人发送应约消息
        return "makePromise";
    }

    private void sendRemind(String userTo) { //发送消息的函数
    }
}

