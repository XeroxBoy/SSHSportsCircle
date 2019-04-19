//package com.cdut.sx.filter;
//
//import com.cdut.sx.websocket.Websocket;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//import java.net.UnknownHostException;
//
//import static com.cdut.sx.utils.Sendmail.sendMail;
//@WebFilter(filterName = "startFilter",urlPatterns = "/*")
//public class startFilter implements Filter {
//    public static int n = 1;
//
//    public void init() throws ServletException {
//        sendMail(n++);
//        this.startWebsocket();
//    }
//
//    public void startWebsocket() {
//
//        int port = 8888; // 端口
//        Websocket s = null;
//        try {
//            s = new Websocket(port);
//            s.start();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest arg0, ServletResponse arg1,
//                         FilterChain arg2) throws IOException, ServletException {
//        // TODO Auto-generated method stub
//        arg2.doFilter(arg0,arg1);
//    }
//}
//
//
