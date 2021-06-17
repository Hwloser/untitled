package com.hwloser.log4j;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger {
  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(Log4jLogger.class);
    logger.debug("Debug Level");
    logger.info("Info Level");
    logger.warn("Warn Level");
    logger.error("Error Level");
  }
}
