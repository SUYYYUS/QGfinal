package com.suyyyus.utils;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/websocket")
public class WebsocketUtils {

    //存放上线的客户端
    private static Set<Session> sessions = new HashSet<>();

    //发送信息
    @OnMessage
    public void onMessage(String  message,Session session) throws IOException {
        broadcast(message,session);
    }

    //有人上线
    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);

        System.out.println("当前聊天室人数为" + sessions.size());
    }

    //有人下线时调用
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);

        System.out.println("有人下线了");
    }

    //把信息广播到每个用户
    private void broadcast(String message, Session sender) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
                System.out.println("Message sent to client: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
