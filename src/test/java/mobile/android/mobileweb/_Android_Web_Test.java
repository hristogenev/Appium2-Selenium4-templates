package mobile.android.mobileweb;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.perfecto.utils.Log;
import io.perfecto.utils.PerfectoTokenStorage;
import io.perfecto.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class _Android_Web_Test {
  private final String cloudName = "demo";
  private AndroidDriver driver;
  private ReportiumClient reportiumClient;
  private WebDriverWait wait;
  @BeforeClass
  public void setUp() throws Exception {
    UiAutomator2Options options = new UiAutomator2Options()
        .setAutomationName("Appium")
        .setPlatformVersion("13")
        .setDeviceName("RFCW2139TEH")
      ;

    Map<String, Object> perfectoOptions = new HashMap<>();
    perfectoOptions.put("securityToken", PerfectoTokenStorage.getTokenForCloud(cloudName));
    /* Add any required perfecto options below */
    perfectoOptions.put("browserName", "Chrome");
    perfectoOptions.put("openDeviceTimeout", 2);
    perfectoOptions.put("enableAppiumBehavior", true);
    perfectoOptions.put("useAppiumForWeb", true);

    options.setCapability("perfecto:options", perfectoOptions);
    Log.info(options.asMap());

    driver = new AndroidDriver(
        new URL("https://" + cloudName + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"), options
    );
    Log.logDriverCapabilities(driver);

    PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
        .withProject(new Project("Perfecto.Support Android Web tests", "1.0"))
        .withJob(new Job("Perfecto.Support Android Web tests", 1))
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
  public void webTest1() throws InterruptedException {
    reportiumClient.testStart("Android Web Test", new TestContext());

    driver.get("https://duckduckgo.com/");
    WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchbox_input")));
    el.sendKeys("Perfecto");
    driver.findElement(By.cssSelector("button[type=submit]")).click();
    Thread.sleep(5000);

    driver.getScreenshotAs(OutputType.FILE);
  }
}
