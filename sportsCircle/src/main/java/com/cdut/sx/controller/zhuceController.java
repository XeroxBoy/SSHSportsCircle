package com.cdut.sx.controller;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.impl.UserdaoImp;
import com.cdut.sx.pojo.message;
import com.cdut.sx.pojo.user;
import com.cdut.sx.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.Map;
import java.util.Set;
@Controller

public class zhuceController {
    @Autowired
    Userdao userdao;

    @Autowired
    private user User;
    public String zhuce(){
        System.out.print(User);
        User.setLastProday(new Date("2000/01/01"));//防止出现不符合现实的打卡结果
        User.setPassword(MD5.encodeMd5(User.getPassword()));
        if(userdao.queryByName(User.getUsername()).isEmpty())
        {
            userdao.save(User);
            return "success";}
        return "fail";
    }



    @Override
    public user getModel() {
        // TODO Auto-generated method stub
        if(User==null)
            return new user();
        else
            return User;
    }
    /**
     * 找到当前用户的信息
     *
     * @return 成功则返回"findMyInfoSucess"
     */
    public String findMyInfo() {
        Map<String, Object> session = ActionContext.getContext().getSession();// 获取当前用户的用户名
        String userName = (String) session.get("name");
        if (userdao.queryByName(userName).size() == 1) {
            user user = userdao.queryByName(userName).get(0);
            this.User = user;
            ActionContext.getContext().getValueStack().push(User);
            return "findMyInfoSuccess";
        } else {
            return "error";// 暂不处理
        }

    }

    /**
     * 用于修改用户信息
     */
    public String modify() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        int userId=(Integer) session.get("id");
        Set<message> message=(Set<message>) session.get("messages");
        //	System.out.println(userId);
        User.setUserId(userId);
        User.setMessages(message);//不然user的message会被清空
        userdao.save(User);
        return "modifySuccess";
    }

    public void validateZhuce(){ //跟数据库中的信息进行比对
        System.out.print(User.getUsername());
        if("".equals(User.getUsername().trim())) //防止它打一堆空格
            this.addFieldError("username", "用户名不能为空！");
        if("".equals(User.getPassword().trim()))
            this.addFieldError("password", "密码不能为空");
    }
}

