package com.hwloser.jdk.log;

import java.util.logging.Logger;

public class JDKLogger {
  public static void main(String[] args) {
      Logger logger = Logger.getLogger("JDK-log");
      logger.info("Hello World");
  }
}
