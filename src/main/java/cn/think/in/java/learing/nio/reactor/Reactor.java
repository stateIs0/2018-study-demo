package cn.think.in.java.learing.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/1-下午4:27
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selected.clear();
            }
        } catch (IOException ex) {

        }
    }

    void dispatch(SelectionKey key) {
        Runnable r = ((Runnable) key.attachment());
        if (r != null) {
            r.run();
        }

    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(selector, c);
                }
            } catch (IOException ex) {

            }
        }
    }

    final class Handler implements Runnable {

        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(128);
        ByteBuffer output = ByteBuffer.allocate(128);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        public Handler(Selector sel, SocketChannel c) throws IOException {
            this.socket = c;
            c.configureBlocking(false);
            // Optionally try first read now
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        boolean inputIsComplete() {
            return false;
        }

        boolean outputIsComplete() {
            return false;
        }

        void process() {

        }
        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDING) {
                    send();
                }
            } catch (IOException ex) {

            }
        }

        private void send() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // Normally also do first write now
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        private void read() throws IOException {
            socket.write(output);
            if (outputIsComplete()) {
                sk.channel();
            }
        }
    }




}
