package mobile.ios.hybridapp;

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
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class _Ios_Hybrid_Test {
  private final String cloudName = "demo";
  private IOSDriver driver;
  private ReportiumClient reportiumClient;
  private WebDriverWait wait;

  @BeforeClass
  public void setUp() throws Exception {

    XCUITestOptions options = new XCUITestOptions()
        .setDeviceName("00008120-001E71991EB8201E")
//        .setPlatformVersion("16.x")
        .setApp("PUBLIC:Praveen/ExpenseTracker/Hybrid/InvoiceHybridApp1.0.ipa")
        .setBundleId("io.perfecto.expense.tracker.hybrid")
        ;

    Map<String, Object> perfectoOptions = new HashMap<>();
    perfectoOptions.put("securityToken", PerfectoTokenStorage.getTokenForCloud(cloudName));
    /* Add any required perfecto options below */
    perfectoOptions.put("openDeviceTimeout", 2);
    perfectoOptions.put("app", "PUBLIC:Praveen/ExpenseTracker/Hybrid/InvoiceHybridApp1.0.ipa");
    perfectoOptions.put("bundleId", "io.perfecto.expense.tracker.hybrid");
    perfectoOptions.put("autoLaunch", true);
    perfectoOptions.put("enableAppiumBehavior", true);
    perfectoOptions.put("useAppiumForHybrid", true);
    perfectoOptions.put("iOSResign", true);

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
  public void iOSHybridTest1() {
    reportiumClient.testStart("iOS Hybrid Test", new TestContext());

    wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Expense Tracker")));

    Utils.printContextHandles(driver);
    Utils.switchToWebViewContext(driver);

    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='login_email']")));
    element.click();
    element.sendKeys("test@perfecto.io");

    element = driver.findElement(By.xpath("//*[@name='login_password']"));
    element.click();
    element.sendKeys("P@ssw0rd");

    driver.findElement(By.id("login_login_btn"))
        .click();

    driver.getScreenshotAs(OutputType.FILE);
  }
}
