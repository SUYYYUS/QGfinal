package com.suyyyus.pojo.TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends Thread{

    private Socket socket;
    public ClientReaderThread(Socket socket){
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

                } catch (Exception e) {
                    System.out.println(socket.getRemoteSocketAddress() + "自己下线了");
                    dataInputStream.close();
                    socket.close();
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
