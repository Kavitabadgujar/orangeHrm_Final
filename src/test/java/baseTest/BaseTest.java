package baseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import java.time.Duration;

public class BaseTest {
   // protected static WebDriver driver;
    public WebDriver driver;
    public static String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public ExtentReports extent;
    public ExtentTest test;

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeTest
    public void getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.setSystemInfo("Project Name", "Selenium Final Project");
            extent.setSystemInfo("Organisation", "org.example");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            extent.attachReporter(sparkReporter);
        }
    }
    //@BeforeSuite
    public WebDriver getDriver(){
        if (driverThreadLocal.get() == null) {
            driver = new ChromeDriver();  // You can use other drivers too
            driverThreadLocal.set(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
            driver.manage().window().maximize();
        }

        return driverThreadLocal.get();
    }
    @AfterMethod
    public void testStatus(ITestResult result) {
        // After each test, check if it failed
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log failure details
            test.log(Status.FAIL, "Test failed due to: " + result.getThrowable());
        }
        extent.flush();
    }

    @AfterSuite
    public void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
      //  extent.flush();
    }

}
