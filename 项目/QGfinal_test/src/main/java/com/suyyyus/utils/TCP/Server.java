package com.suyyyus.utils.TCP;

import com.suyyyus.pojo.TCP.ServerReaderThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket> onlineSockets = new ArrayList<>();

    //启动学习讨论区服务端
    public static void startDiscussionArea() throws IOException {

        System.out.println("===学习讨论区启动===");
        //创建服务端对象serversocket
        ServerSocket serverSocket = new ServerSocket(8080);


        while (true) {

            //等待客户端的连接
            Socket socket = serverSocket.accept();
            onlineSockets.add(socket);

            System.out.println(socket.getRemoteSocketAddress() + "上线了");
            System.out.println("================");

            //交给一个独立的线程进行负责
            new ServerReaderThread(socket).start();

        }


//        //1.从socket中获取字节输入流
//        InputStream inputStream = accept.getInputStream();
//
//        DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//        while (true) {
//            try {
//                //方法要一致
//                String s = dataInputStream.readUTF();
//
//                System.out.println(s);
//
//                System.out.println(accept.getRemoteSocketAddress());
//
//                System.out.println("======================");
//            } catch (IOException e) {
//                System.out.println(accept.getRemoteSocketAddress() + "离线了");
//                dataInputStream.close();
//                accept.close();
//                break;
//            }
//        }


    }

}
