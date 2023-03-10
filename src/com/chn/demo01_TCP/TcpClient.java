package com.chn.demo01_TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author chenghn
 * @create 2023-01-06
 */

@SuppressWarnings({"all"})
public class TcpClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 6666);
        OutputStream os = socket.getOutputStream();
        os.write("你好服务器".getBytes());

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));
        socket.close();


    }
}
