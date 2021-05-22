package com.hwloser.io.discard.server.netty;

public class DiscardTestMain {
    public static void main(String[] args) throws InterruptedException {
        int port = 1234;
        String hostName = "192.168.56.1";

        DiscardServer server = new DiscardServer(hostName, port);
        server.run();



    }
}
