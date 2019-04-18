package com.cdut.sx.filter;

import com.cdut.sx.websocket.Websocket;

import javax.servlet.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class startFilter implements Filter {

    public void init(FilterConfig fc) throws ServletException {
        this.startWebsocket();

    }

    public void startWebsocket() {

        int port = 8888; // 端口
        Websocket s = null;
        try {
            s = new Websocket(port);
            s.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        // TODO Auto-generated method stub

    }
}
	

