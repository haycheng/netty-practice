package net.melody.nettypractice.reactor.trial;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author chenglx
 * @version V1.0
 * @date 2021/1/27 16:48
 * <p>
 * Date Author Description
 * ------------------------------------------------------
 * 2021/1/27 chenglx init
 */
public class Handler implements Runnable {

    public static final int MAX_IN = 1024;
    public static final int MAX_OUT = 1024;

    final SocketChannel channel;
    final SelectionKey key;
    ByteBuffer input = ByteBuffer.allocate(MAX_IN);
    ByteBuffer output = ByteBuffer.allocate(MAX_OUT);

    public static final int READING = 0, SENDING = 1;
    int status = READING;

    public Handler(SocketChannel sc, Selector s) throws IOException {
        channel = sc;
        channel.configureBlocking(false);
        key = channel.register(s, 0);
        key.attach(this);
        key.interestOps(SelectionKey.OP_READ);
    }

    @Override
    public void run() {

    }
}
