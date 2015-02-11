package Nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by wzyoung on 2015/2/12.
 */
public class UnblockClient {
    public static void main(String[] args) {
        try {

            InetSocketAddress ia = new InetSocketAddress(InetAddress.getLocalHost(), 8710);

            //客户端要使用SocketChannel，对应服务器的ServerSocketChannel
            SocketChannel client = SocketChannel.open();
            client.connect(ia);
            //设置为非阻塞，否则就和刚才的程序没啥区别了
            client.configureBlocking(false);

            //发送数据
            sendMessage(client);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int sendMessage(SocketChannel client) {
        System.out.println("Inside SendMessage");
        String msg;
        ByteBuffer bytebuf;
        int nBytes = 0;
        try {
            msg = "It's message from client!";
            System.out.println("msg is " + msg);
            bytebuf = ByteBuffer.wrap(msg.getBytes());
            for (int i = 0; i < 3; i++) {
                nBytes = client.write(bytebuf);
                System.out.println(i + " finished");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            client.close();
            return -1;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nBytes;
    }
}
