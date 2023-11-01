package io.perfecto.utils;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Log {
  private static final Logger logger = LoggerFactory.getLogger(Log.class);

  public static void info(String msg) {
    logger.info(msg);
  }

  public static void info (Map<String, Object> map) {

    logger.info("Options in use:");
    for (Map.Entry opt : map.entrySet()) {
      String key = opt.getKey().toString();
      if (key == "perfecto:options") {

      }
      logger.info(String.format("%s: %s", opt.getKey(), opt.getValue()));
    }
  }
  public static void debug(String msg) {
    logger.debug(msg);
  }

  public static void error(String msg) {
    logger.error(msg);
  }

  public static void error(String msg, Throwable ex) {
    logger.error(msg, ex);
  }

  public static void error(Throwable ex) {
    ex.printStackTrace();
  }
  public static void warn(String msg) {
    logger.warn(msg);
  }

  public static void logDriverCapabilities(RemoteWebDriver driver) {
    logger.info("Created driver of type " + driver);
    logger.info("********************************************************************");
    logger.info("Capabilities in use: ");
    logger.info("********************************************************************");
    for (Map.Entry cap : driver.getCapabilities().asMap().entrySet()) {
      logger.info(String.format("%s: %s", cap.getKey().toString(), cap.getValue()));
    }
  }
}
