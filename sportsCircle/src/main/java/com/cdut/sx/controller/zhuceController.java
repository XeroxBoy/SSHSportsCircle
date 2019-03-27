package com.cdut.sx.controller;

import com.cdut.sx.pojo.message;
import com.cdut.sx.pojo.user;
import com.cdut.sx.service.UserService;
import com.cdut.sx.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;

@Controller
public class zhuceController {
    @Autowired
    UserService userdao;

    @Autowired
    private user User;

    @RequestMapping("/zhuce")
    public ModelAndView zhuce(@Validated @ModelAttribute("user") user User) {
        ModelAndView mav = new ModelAndView("views/fitcircle");
        ModelAndView errormav = new ModelAndView("views/error");
        User.setLastProday(new Date("2000/01/01"));//防止出现不符合现实的打卡结果
        User.setPassword(MD5.encodeMd5(User.getPassword()));
        if (userdao.queryByName(User.getUsername()).isEmpty()) {
            userdao.save(User);
            return mav;
        }
        return errormav;
    }


    /**
     * 找到当前用户的信息
     *
     * @return 成功则返回"findMyInfoSucess"
     */
    @RequestMapping("/findMyInfo")
    public ModelAndView findMyInfo(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/MyInfo");
        ModelAndView errormav = new ModelAndView("views/error");
        String userName = (String) session.getAttribute("name");
        if (userdao.queryByName(userName).size() == 1) {
            user user = userdao.queryByName(userName).get(0);
            this.User = user;
            mav.addObject("User", User);
            return mav;
        } else {
            return errormav;// 暂不处理
        }

    }

    /**
     * 用于修改用户信息
     */
    @RequestMapping("/modify")
    public ModelAndView modify(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/MyInfo");
        int userId = (Integer) session.getAttribute("id");
        Set<message> message = (Set<message>) session.getAttribute("messages");
        User.setUserId(userId);
        User.setMessages(message);//不然user的message会被清空
        userdao.save(User);
        return mav;
    }
}
