package Nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wzyoung on 2015/2/12.
 */
public class UnblockServer {
    public static void main(String[] args) {
        SocketChannel socket = null;
        try {
            // 打开一个选择器
            Selector selector = Selector.open();

            // 客户端要使用SocketChannel，对应服务器的ServerSocketChannel
            ServerSocketChannel ssc = ServerSocketChannel.open();

            // 绑定服务器IP和端口
            InetSocketAddress isa = new InetSocketAddress(8710);
            ssc.socket().bind(isa);
            ssc.configureBlocking(false);

            // 向selector注册，要处理的是接收传入事件，所以权限设置为OP_ACCEPT
            SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

            // 操作？
            while (selector.select() > 0) {
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator it = readyKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        System.out.println("Key is Acceptable");
                        socket = ssc.accept();
                        socket.configureBlocking(false);
                        socket.register(selector,
                                SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if (key.isReadable()) {
                        System.out.println("Key is readable");
                        socket = (SocketChannel) key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        socket.read(buf);
                        buf.flip();
                        // 这段实用
                        Charset charset = Charset.forName("us-ascii");
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer charBuffer = decoder.decode(buf);
                        String result = charBuffer.toString();
                        System.out.println("Receive Data:" + result);
                    }
                    if (key.isWritable()) {
                        System.out.println("Key is writable");
                        String msg = "Message from server";
                        socket = (SocketChannel) key.channel();
                        //类型转换，实用
                        Charset set = Charset.forName("us-ascii");
                        CharsetDecoder dec = set.newDecoder();
                        CharBuffer charBuf = dec.decode(ByteBuffer.wrap(msg.getBytes()));
                        System.out.println("Message from server:" + charBuf.toString());
                        int nBytes = socket.write(ByteBuffer.wrap((charBuf.toString()).getBytes()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
