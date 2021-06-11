package com.hwloser.simple;

import java.io.IOException;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;

public class YarnClientDemo {

  private static YarnClient yarnClient;

  public static void main(String[] args) throws IOException, YarnException {
    // 首先初始化和启动YarnClient
    initYarnClient();

    yarnClient.getLabelsToNodes().forEach(System.out::printf);
  }

  private static void initYarnClient() {
    YarnConfiguration conf = new YarnConfiguration();
    yarnClient = YarnClient.createYarnClient();
    yarnClient.init(conf);
    yarnClient.start();

  }

}
