package com.cdut.sx.websocket;

import com.cdut.sx.pojo.User;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.Session;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Websocket extends WebSocketServer {
    private static Thread t = new Thread();
    private static int onlineCount = 0;

    private static Map<User, Websocket> clients = new ConcurrentHashMap<User, Websocket>();

    private Session session;

    private User user;

    private static int id = 0;

    public Websocket(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
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
        String userFrom = message.split(" ")[0];
        String userTo = message.split(" ")[1];
        Set<User> users = clients.keySet();
        for (User user : users) {
            if (user.getUsername().equals(userTo)) {
                WebsocketService.sendMessage(message, userTo);
            }
        }
    }


    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Websocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Websocket.onlineCount--;
    }


    public static synchronized Map<User, Websocket> getClients() {
        return clients;
    }

}
	


