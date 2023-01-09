package com.chn.demo02_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author chenghn
 * @create 2023-01-06
 */
@SuppressWarnings({"all"})
public class TcpServer {
    /*y优化：
    1、需自定义定义命名规则
    2、防止多客户端上传，让服务器一直处于监听状态，有一个客户端上传文件就接收一个（即使用死循环一直接受文件）
    3、优化效率，使用多线程（还可以使用线程池）
    * */

    public static void main(String[] args) throws IOException {

        //1、创建一个ServerSocket对象，指定的端口
        ServerSocket serverSocket = new ServerSocket(6666);


        //*优化效率，使用多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //*让服务器一直处于监听状态
                    while (true){
                        //2、接收客户端的socket连接，
                        Socket socket = serverSocket.accept();

                        //3、获取客户端网络输入流
                        InputStream is = socket.getInputStream();

                        //4、判断文件夹imge是否存在
                        File file = new File("D:\\图片包\\imge");
                        if (!file.exists()){
                            file.mkdir();
                        }
                        //5、创建本地输出流（注意文件名被写死，需自定义定义命名规则）
                        String filename = "imge"+System.currentTimeMillis()+new Random().nextInt(1000)+".jpg";
                        FileOutputStream fos = new FileOutputStream( file+"\\"+filename);

                        //6、使用网络输入流read文件，再使用本地输出流写入磁盘
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while ((len = is.read(bytes))!=-1){
                            fos.write(bytes,0,len);
                        }

                        //7、服务端回写数据
                        OutputStream os = socket.getOutputStream();
                        os.write("上传成功".getBytes());

                        //8、关闭资源
                        socket.close();

                        fos.close();
                    }
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }).start();



        //*用于服务器一直处于监听状态，无需关闭资源
        //serverSocket.close();



    }


}
