package com.cdut.sx.controller;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class loginController {
    Userdao userdao=new UserdaoImp();
    private user User = new user();

    public static Date getNextDay(Date date) {//获取今天的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    @SuppressWarnings("deprecation")
    public String user(){ //验证方法 进行数据库操作

        Map<String,Object> session=ActionContext.getContext().getSession();//将用户信息存入session
        HttpServletRequest request = ServletActionContext.getRequest();
        String remember=request.getParameter("remember");//是否勾选记住账号密码
        String code=request.getParameter("code");//获取用户输入的验证码
        HttpServletResponse response=ServletActionContext.getResponse();
        List<user> user=userdao.queryByName(User.getUsername());//获取用户信息
        String reqPass = MD5.encodeMd5(User.getPassword());

        if(user.size()!=0){
            user user1=user.get(0);//获取数据库中的对象
            if (user1 == null || !user1.getPassword().equals(reqPass))//没查到用户信息,或者密码不匹配
                return "fail"; //查询失败 去失败页面
            if(!code.equals((String)session.get("randstr"))){//验证码数错了 不能用== 不然比较的是引用
                System.out.println(code+"   "+session.get("randstr"));
                return "fail";
            }
            if(remember!=null)
                this.setCookie();//把用户信息保存到COOKIE中

            int date=user1.getLastProday().getDate();//获取上次打卡的日期
            Date beforeday=LoginAction.getNextDay(new Date());//获取昨天

            if(user1.getLastProday().getDate()!=beforeday.getDate() && user1.getLastProday().getDate()!=new Date().getDate())
            {//如果昨天今天都没打卡
                user1.setProdays(0);//连续打卡天数设为0
                userdao.update(user1);
            }

            if(user1.getProdays()!=0){//连续打卡了
                if(user1.getLastProday().equals(beforeday) && user1.getLastProday().getMonth()!=new Date().getMonth()){
                    date=date-user1.getProdays();//处理打卡日期月底和月初连续的情况 适应js中的日历函数 比如说31，1，2打了三次卡
                    //那他这个月应该画勾的日期会从 2-3=-1算起 打3个勾 最终效果就是1，2号打勾

                }
            }
            session.put("id", user1.getUserId());//把用户信息存到session 以供其他页面使用
            System.out.println(user1.getUserId());
            session.put("name",user1.getUsername());
            session.put("email",user1.getEmail());
            session.put("birthday",user1.getBirthday());
            session.put("lastProdays", date);
            session.put("maxProdays",user1.getMaxProdays());
            session.put("prodays", user1.getProdays());
            session.put("sex", user1.getSex());
            session.put("area",user1.getAreabelongto());
            session.put("messages", user1.getMessages());
            return "success";} else
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

    @Override
    public void validate() {
        if("".equals(User.getUsername().trim())){
            this.addFieldError("username", "用户名不能为空！");
            if("".equals(User.getPassword().trim())){
                this.addFieldError("password", "密码不能为空");
            }
        }
    }

    public void setCookie(){
        HttpServletResponse response = ServletActionContext.getResponse();
        Cookie cookie1=new Cookie("zh",User.getUsername());

        Cookie cookie2=new Cookie("mm",User.getPassword());

        cookie1.setMaxAge(60*60*24);//保存一天
        cookie2.setMaxAge(60*60*24);
        cookie1.setPath("/");//保存路径，不设这个保存不好
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }
}

