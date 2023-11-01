package mobile.ios.nativeapp;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.perfecto.utils.Log;
import io.perfecto.utils.PerfectoTokenStorage;
import io.perfecto.utils.Utils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class _Ios_Native_Test {
  private final String cloudName = "demo";
  private IOSDriver driver;
  private ReportiumClient reportiumClient;
  private WebDriverWait wait;

  @BeforeClass
  public void setUp() throws Exception {

    XCUITestOptions options = new XCUITestOptions()
        .setDeviceName("")
        .setBundleId("com.apple.Preferences")
        ;

    Map<String, Object> perfectoOptions = new HashMap<>();
    perfectoOptions.put("securityToken", PerfectoTokenStorage.getTokenForCloud(cloudName));
    /* Add any required perfecto options below */
    perfectoOptions.put("openDeviceTimeout", 2);
    perfectoOptions.put("enableAppiumBehavior", true);
//    perfectoOptions.put("useAppiumForHybrid", true);
//    perfectoOptions.put("useAppiumForWeb", true);

    options.setCapability("perfecto:options", perfectoOptions);

    driver = new IOSDriver(
        new URL("https://" + cloudName + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"), options
    );
    Log.logDriverCapabilities(driver);

    PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
        .withProject(new Project("Perfecto.Support iOS tests", "1.0"))
        .withJob(new Job("Perfecto.Support iOS tests", 1))
        .withWebDriver(driver)
        .build();
    reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

    wait = new WebDriverWait(driver, Duration.ofSeconds(15));

  }

  @AfterMethod
  public void testEnded(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      reportiumClient.testStop(TestResultFactory.createFailure(result.getThrowable()));
    }
    else {
      reportiumClient.testStop(TestResultFactory.createSuccess());
    }
  }

  @AfterClass
  public void tearDown() {
    Utils.openReportUrl(driver);
    driver.quit();
  }

  @Test
  public void iOSTest1() {
    reportiumClient.testStart("iOS Native Test", new TestContext());
    driver.activateApp("com.apple.Preferences");
  }
}
