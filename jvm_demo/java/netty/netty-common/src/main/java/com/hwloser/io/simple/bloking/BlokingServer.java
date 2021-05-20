package com.hwloser.io.simple.bloking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlokingServer {
  public static void main(String[] args) {
    int port = 8088;
    try (ServerSocket ss = new ServerSocket(port);) {
      Socket client = ss.accept();
      BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));

      System.out.println("request: ");
      bf.lines().forEach(System.out::println);

      PrintWriter out = new PrintWriter(client.getOutputStream());
      out.println("accept request");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
