package com.cdut.sx.controller;

import com.cdut.sx.pojo.User;
import com.cdut.sx.service.UserService;
import com.cdut.sx.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserService userdao;
    @Autowired
    private User User;

    public static Date getNextDay(Date date) {//获取今天的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = (Date) calendar.getTime();
        return date;
    }
    @RequestMapping("/")
    public String start(){
        return "views/Login";
    }

    /**
     * @param User
     * @param session
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/login")
    public ModelAndView user(@Validated @ModelAttribute User User, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {
        this.User = User; //验证方法 进行数据库操作
        String remember = request.getParameter("remember");//是否勾选记住账号密码
        String code = request.getParameter("code");//获取用户输入的验证码
        List<User> user = userdao.queryByName(User.getUsername());//获取用户信息
        String reqPass = MD5.encodeMd5(User.getPassword());
        ModelAndView mav = new ModelAndView("forward:/findArea");
        ModelAndView errormav = new ModelAndView("redirect:views/Login");
        if (!user.isEmpty()) {
            User user1 = user.get(0);//获取数据库中的对象
            if (user1 == null || !user1.getPassword().equals(reqPass))//没查到用户信息,或者密码不匹配
                return errormav; //查询失败 去失败页面
            if (!code.equals(String.valueOf(session.getAttribute("randstr")))) {//验证码数错了 不能用== 不然比较的是引用
                return errormav;
            }
            if (remember != null)
                this.setCookie(response);//把用户信息保存到COOKIE中

            int date = user1.getLastProday().getDate();//获取上次打卡的日期
            Date beforeday = getNextDay(new Date());//获取昨天

            if (user1.getLastProday().getDate() != beforeday.getDate() && user1.getLastProday().getDate() != new Date().getDate()) {//如果昨天今天都没打卡
                user1.setProdays(0);//连续打卡天数设为0
                userdao.save(user1);
            }

            if (user1.getProdays() != 0) {//连续打卡了
                if (!user1.getLastProday().equals(beforeday) || user1.getLastProday().getMonth() == new Date().getMonth()) {

                } else {
                    date = date - user1.getProdays();//处理打卡日期月底和月初连续的情况 适应js中的日历函数 比如说31，1，2打了三次卡
                    //那他这个月应该画勾的日期会从 2-3=-1算起 打3个勾 最终效果就是1，2号打勾

                }
            }
            session.setAttribute("id", user1.getUserId());//把用户信息存到session 以供其他页面使用
            session.setAttribute("name", user1.getUsername());
            session.setAttribute("email", user1.getEmail());
            session.setAttribute("birthday", user1.getBirthday());
            session.setAttribute("lastProdays", date);
            session.setAttribute("maxProdays", user1.getMaxProdays());
            session.setAttribute("prodays", user1.getProdays());
            session.setAttribute("sex", user1.getSex());
            session.setAttribute("area", user1.getAreabelongto());
            session.setAttribute("myCircles", user1.getCircles());
            return mav;
        } else
            return errormav;
    }


    public void setCookie(HttpServletResponse response) {
        Cookie cookie1 = new Cookie("zh", User.getUsername());

        Cookie cookie2 = new Cookie("mm", User.getPassword());

        cookie1.setMaxAge(60 * 60 * 24);//保存一天
        cookie2.setMaxAge(60 * 60 * 24);
        cookie1.setPath("/");//保存路径，不设这个保存不好
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }
    @RequestMapping("/logout")
    public ModelAndView logout(){
        return new ModelAndView("views/Login");
    }
    @RequestMapping("/toError")
    public ModelAndView error(){
        return new ModelAndView("views/error");
    }
}

