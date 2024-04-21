package com.suyyyus.pojo.TCP;

import com.suyyyus.utils.TCP.Server;

import java.io.*;
import java.net.Socket;

public class ServerReaderThread extends Thread{

    private Socket socket;

    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while(true){
                String s = null;
                try {
                    s = dataInputStream.readUTF();
                    System.out.println(s);
                    //把信息转发给其他人
                    sentmsgToAll(s);
                } catch (Exception e) {
                    System.out.println(socket.getRemoteSocketAddress() + "下线了");
                    Server.onlineSockets.remove(socket);
                    dataInputStream.close();
                    socket.close();
                    break;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sentmsgToAll(String msg) throws IOException {

        //遍历所有在线socket
        for (Socket onlineSocket : Server.onlineSockets) {
            OutputStream outputStream = onlineSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }


    }


}
