package com.chn.demo01_TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chenghn
 * @create 2023-01-06
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(6666);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();

        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));

        //回写
        OutputStream os = socket.getOutputStream();
        os.write("你好客户端，我已收到".getBytes());
        socket.close();
        serverSocket.close();
    }



}
