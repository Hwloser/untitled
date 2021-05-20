package com.hwloser.thrift.server;

import com.hwloser.thrift.service.UserService.Iface;
import com.hwloser.thrift.service.UserService.Processor;
import com.hwloser.thrift.service.UserServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单线程 demo
 */
public class Server {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private void startServer() throws TTransportException {
    Processor<Iface> processor = new Processor<>(new UserServiceImpl());

    TServerTransport transport = new TServerSocket(1234);

    TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport)
        .processor(processor)
        .protocolFactory(new TBinaryProtocol.Factory())
        .transportFactory(new TTransportFactory())
        .minWorkerThreads(10)
        .maxWorkerThreads(20);

    TThreadPoolServer server = new TThreadPoolServer(tArgs);
    server.serve();
  }

  public static void main(String[] args) throws TTransportException {
    Server s = new Server();
    s.startServer();
  }
}
