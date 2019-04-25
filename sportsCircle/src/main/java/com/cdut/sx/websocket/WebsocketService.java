package com.cdut.sx.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
public class WebsocketService {
    WebsocketService() {
    }

    public static Map<Session, String> getUserconnections() {
        return userconnections;
    }

    private static final Map<Session, String> userconnections = new HashMap<Session, String>();

    //向连接池中添加连接
    void addUser(String user, Session conn) {
        userconnections.put(conn, user); // 添加连接
    }

    // 移除连接池中的连接
    boolean removeUser(Session conn) {
        if (userconnections.containsKey(conn)) {
            userconnections.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    // 向所有的用户发送消息
    void sendMessagetoAll(String message) throws IOException {
        Set<Session> keySet = userconnections.keySet(); //获取目前用户的socket
            for (Session conn : keySet) {
                String user = userconnections.get(conn);
                if (user != null && conn.isOpen()) {
                    System.out.println("开始向其中" + userconnections.get(conn) + "用户发送信息" + conn.getId());
//                    conn.getBasicRemote().sendText(message);
                    synchronized (conn) {
                        conn.getBasicRemote().sendText(userconnections.get(conn) + ": " + message);
                    }
                }

        }
    }

    void sendMessage(String message, String username) throws IOException {
        Set<Session> keySet = userconnections.keySet(); //获取目前用户的socket
        Set<Map.Entry<Session, String>> messageEntry = userconnections.entrySet();
        synchronized (messageEntry) {
            for (Map.Entry<Session, String> connection : messageEntry) {
                if (connection.getValue().equals(username) && connection.getKey().isOpen()) {
                    System.out.println("开始给" + connection.getValue() + "用户发送信息" + connection.getKey().getId());
                    Session conn = connection.getKey();
                    synchronized (conn) {
                        conn.getBasicRemote().sendText(connection.getValue() + ": " + message);
                    }
                }
            }
        }
    }


    public boolean isEmpty() {
        return userconnections.size() == 0;
    }

}



