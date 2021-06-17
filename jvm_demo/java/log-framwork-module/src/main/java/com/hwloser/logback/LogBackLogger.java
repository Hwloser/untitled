package com.hwloser.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackLogger {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(LogBackLogger.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");
  }
}
