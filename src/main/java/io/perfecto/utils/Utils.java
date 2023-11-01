package io.perfecto.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class Utils {
  private final static Logger logger = LoggerFactory.getLogger(Utils.class);

  public static String getReportUrl(RemoteWebDriver driver) {
    try {
      return ((String)driver.getCapabilities().getCapability("testGridReportUrl"))
          .replace("[", "%5B").replace("]", "%5D");
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public static void openReportUrl(String reportUrl) {
    try {
      logger.info("*******************************************************************");
      logger.info("Report URL {}", reportUrl);
      logger.info("*******************************************************************");
      java.awt.Desktop.getDesktop().browse(new URI(reportUrl));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }
  }

  public static void openReportUrl(RemoteWebDriver driver) {
    try {
      String reportUrl = getReportUrl(driver);
      java.awt.Desktop.getDesktop().browse(new URI(reportUrl));
      logger.info("*******************************************************************");
      logger.info("Report URL {}", reportUrl);
      logger.info("*******************************************************************");
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }
  }

  public static void printContextHandles(SupportsContextSwitching driver) {
    Log.info("Available context handles:");
    for (String context : driver.getContextHandles()) {
      logger.info(context);
    }
  }

  public static void switchToWebViewContext(SupportsContextSwitching driver) {
    for (String context : driver.getContextHandles()) {
      if (context.toUpperCase().startsWith("WEBVIEW")){
        Log.info("Switching to " + context);
        driver.context(context);
        return;
      }
    }
    Log.warn("No WebView context found!");
    printContextHandles(driver);
  }
}
