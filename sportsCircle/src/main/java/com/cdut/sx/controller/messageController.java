package com.cdut.sx.controller;

import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.message;
import com.cdut.sx.pojo.remind;
import com.cdut.sx.service.messagedaoImp;
import com.cdut.sx.service.reminddaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@RestController
public class messageController {
    @Autowired
    reminddaoImp reminddao;
    @Autowired
    private message message;
    private Integer currPage = 1;
    @Autowired
    private messagedaoImp dao;
    @RequestMapping("/message")
    public ModelAndView message(HttpSession session) { //验证状态是否合理
        message.setActive("active");
        if (message == null || session.getAttribute("name") == null)//未登录
            return new ModelAndView("views/error");
        message.setUserId((String) session.getAttribute("name"));//给message所属人属性赋值
        dao.save(message);
        return new ModelAndView("views/fitcircle");
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        System.out.println("进入findAll");
        PageBean<message> pageBean = dao.findByPage(currPage);
        //System.out.println("执行了findBypage,接下来遍历pageBean里面的list");
/*	for(int i=0;i<pageBean.getList().size();++i)
	{
		System.out.println(pageBean.getList().get(i));
	}*/

        //将pageBean存入到值栈中
        ModelAndView mav = new ModelAndView("views/fitcircle");
        mav.addObject("pageBean", pageBean);
        return mav;
    }
    @RequestMapping("/solve")
    public String solve(HttpServletRequest request) { //将message状态置为解决
        String messageId = request.getParameter("messageId");
        message message = dao.queryById(Integer.valueOf(messageId));
        message.setActive("dead");
        return "views/fitcircle";
    }

    /**
     * 查找不同领域（打球圈,健身圈，跑步圈，……）
     *
     * @return
     */
    @RequestMapping("/findArea")
    public ModelAndView findArea(HttpServletRequest request, HttpSession session) {
	/*	messagedao Messagedao=new messagedaoImp();
		message message1=Messagedao.queryById(message.getMessageid()).get(0);
		message=message1;//把评论给他
*/
        //	System.out.println(message.getComments());
        ModelAndView mav = new ModelAndView("views/fitcircle");
        String username = (String) session.getAttribute("name");
        ArrayList<remind> reminds = reminddao.queryAll(username);

        String area = request.getParameter("area");//如果是点链接跳过来的话
        if (area == null)//如果是通过登录等方式跳过来的话
            area = (String) session.getAttribute("area");
        else {//如果是点链接跳过来的话
            try {
                area = new String(URLDecoder.decode(area, "utf-8"));//转码
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("area" + area);
        if (currPage == 0)
            currPage = 1;
        session.setAttribute("area", area);//重新赋值 分页查询才会正确显示其他圈子的状态
        //System.out.print(area);
        PageBean<message> pageBean = dao.findByArea(currPage, area);
        for (int i = 0; i < pageBean.getList().size(); i++) {
            System.out.println(pageBean.getList().get(i));
        }
        mav.addObject("pageBean", pageBean);
        return mav;

    }

    /**
     * 查找我的发出的messages
     *
     * @return
     */
    @RequestMapping("/findMine")
    public ModelAndView findMine(HttpSession session) {
        //	System.out.println("进入了findMine()");
        ModelAndView mav = new ModelAndView("views/fitcircle");
        String userId;
        userId = String.valueOf(session.getAttribute("id")); // Servlet 中获取 Session 对象
        System.out.println(userId);
        PageBean<message> pageBean = dao.findMineByPage(currPage, userId);
        for (int i = 0; i < pageBean.getList().size(); ++i) {
            System.out.println(pageBean.getList().get(i));
        }
        mav.addObject("pageBean", pageBean);
        return mav;
    }

//    public String sendPromise() {
//        //	HttpServletRequest request = ServletActionContext.getRequest();
//        //	request.getParameter("")
//        sendMessageToHost();//给楼主发送通知
//        return "sendPromiseSuccess";
//    }

//    private void sendMessageToHost() {
//    }
//    @RequestMapping("/makePromise")
//    public ModelAndView makePromise() {
//        ModelAndView mav = new ModelAndView("views/fitcircle");
//        message.setActive("dead");
//        dao.save(message);
//        return mav;
//    }
    @RequestMapping("/deleteMessage")
    public ModelAndView delete(HttpServletRequest request) {
        int messageid = Integer.parseInt(request.getParameter("messageid"));
        message messages = dao.queryById(messageid);
        if (messages != null) {
            messages.setActive("dead");
        }
        dao.save(messages);
        ModelAndView mav = new ModelAndView("views/fitcircle");
        return mav;
    }
}

