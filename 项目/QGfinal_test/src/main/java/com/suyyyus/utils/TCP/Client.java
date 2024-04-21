package com.suyyyus.utils.TCP;

import com.suyyyus.pojo.TCP.ClientReaderThread;
import junit.framework.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * 客户端
 */
public class Client {


    public static void sendMsg(String name) throws IOException {
        //1.创建客户端对象，并同时请求与服务器进行连接
        Socket socket = new Socket("127.0.0.1",8080);

        //创建独立线程，随时接收服务端发送过来的消息
        new ClientReaderThread(socket).start();

        //2.从socket中获得一个字节输出流，发送信息到服务端
        OutputStream outputStream = socket.getOutputStream();

        //把字节变成数据的
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

//        Scanner sc = new Scanner(System.in);

//        while (true) {

            String s1 = name;
//            System.out.println("请说：");
////            String s = sc.next();
//
//            if("exit".equals(s)){
//                System.out.println("下次再来");
//                dataOutputStream.close();
//                socket.close();
//                break;
//            }
            //发送出去
            dataOutputStream.writeUTF(s1);
            dataOutputStream.flush();


//        }
                System.out.println("发送成功========");
//                dataOutputStream.close();
//                socket.close();

    }


}
