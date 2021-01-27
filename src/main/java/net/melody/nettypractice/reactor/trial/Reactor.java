package net.melody.nettypractice.reactor.trial;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chenglx
 * @version V1.0
 * @date 2021/1/27 16:31
 * <p>
 * Date Author Description
 * ------------------------------------------------------
 * 2021/1/27 chenglx init
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel channel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> itr = set.iterator();
                while (itr.hasNext()) {
                    dispatch(itr.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务处理
     * @param key
     */
    void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {
        public void run() {
            try {
                SocketChannel c = channel.accept();
                if (c != null) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
