package com.hwloser.thrift.client;

import com.hwloser.thrift.service.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;

public class Client {

  private void startClient() throws TException {
    int port = 1234;
    int timeout = 1234;
    TSocket s = new TSocket("localhost", port, timeout);
    UserService.Client client = new UserService.Client(new TBinaryProtocol(s));

    s.open();

    System.out.println(client.getName(1));
    System.out.println(client.isExist("haha"));
  }

  public static void main(String[] args) throws TException {
    Client c = new Client();
    c.startClient();
  }
}
