package com.hwloser.simple;

import java.io.IOException;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;

public class YarnClientDemo {

  public static void main(String[] args) throws Exception {
    // 首先初始化和启动YarnClient
    YarnClient yarnClient = initYarnClient();

    applicationResponse(yarnClient);

  }

  private static void applicationSubmissionContext(YarnClient yarn) {
    
  }

  private static void applicationResponse(YarnClient yarn) throws IOException, YarnException {
    System.out.println(yarn.getAllQueues());
//    System.out.println(yarn.getResourceProfiles()); // current unable
  }

  private static YarnClient initYarnClient() {
    YarnConfiguration conf = new YarnConfiguration();
    YarnClient yarnClient = YarnClient.createYarnClient();
    yarnClient.init(conf);
    yarnClient.start();
    return yarnClient;
  }

}

