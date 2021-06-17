package com.hwloser.logback.simple;

import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackLogger {
  public static void main(String[] args) {
    // slf4j facade
    simpleLogbackDemo();

    // 标准错误流
    System.out.println(" --------------------------- ");
    sysErrorDemo();
  }

  private static void simpleLogbackDemo() {
    Logger logger = LoggerFactory.getLogger(LogBackLogger.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");
  }

  private static void sysErrorDemo() {
    PrintWriter p = new PrintWriter(System.err);
    p.println("error");
    p.flush();
  }
}
