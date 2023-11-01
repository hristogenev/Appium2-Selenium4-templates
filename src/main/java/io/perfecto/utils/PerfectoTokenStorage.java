package io.perfecto.utils;

import java.io.InputStream;
import java.util.Properties;

public class PerfectoTokenStorage {

  public static String getTokenForCloud(String cloudName) throws Exception {

    InputStream input = PerfectoTokenStorage.class.getClassLoader().getResourceAsStream("tokens.ini");
    if (input == null) {
      throw new Exception("Sorry, unable to find tokens file in resources folder!");
    }

    Properties prop = new Properties();
    prop.load(input);

    String token = prop.getProperty(cloudName);
    if (token == null)
      throw new Exception(String.format("Token for cloud %s was not found!", cloudName));

    return token;
  }
}
