package com.chn.demo02_TCP;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author chenghn
 * @create 2023-01-06
 */
public class TcpClient {
    public static void main(String[] args) throws Exception {
        //1、创建一个本地字节输入流FileInputStream对象，构造方法中绑定要读取的数据源。
        FileInputStream fis = new FileInputStream("D:\\图片包\\wpcache\\2052239.jpg");

        //2、创建一个socket对象
        Socket socket = new Socket("127.0.0.1", 6666);

        //3、获取客户端的网络输出流
        OutputStream os = socket.getOutputStream();
        //4、本地输入读取文件，网络输出流向服务端上传文件
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }

        //**注意：在此处需手动结束输出流,否则读写循环结束不了，出现阻塞现象
        socket.shutdownOutput();

        //5、网络输入流接收服务端的回写数据
        InputStream is = socket.getInputStream();
        while((len = is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }

        //6、释放资源
        fis.close();
        socket.close();


    }


}
