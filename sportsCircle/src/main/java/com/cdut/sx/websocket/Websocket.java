package com.cdut.sx.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

/*
import javax.mail.Session;
*/
public class Websocket extends WebSocketServer {

    public Websocket(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println(port);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onClose(WebSocket conn, int message, String reason, boolean remote) {
        // TODO Auto-generated method stub
        WebsocketService.removeUser(conn);
        WebsocketService.sendMessage("用户下线了");

    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // TODO Auto-generated method stub
        WebsocketService.addUser(message, conn);
        while (!WebsocketService.isEmpty()) {//所有用户退出之前
            Thread t = new Thread();
            WebsocketService.sendMessage("系统时间：" + new Date());
            try {
                t.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub

    }

}
	


