package baseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
   // protected static WebDriver driver;
    public WebDriver driver;
    public static String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static ExtentReports extent;
    public static ExtentSparkReporter sparkReporter;
    public ExtentTest test;

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeTest
    public void getExtent() {
        //if (extent == null) {
            extent = new ExtentReports();
            extent.setSystemInfo("Project Name", "Selenium Final Project");
            extent.setSystemInfo("Organisation", "org.example");
            sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            extent.attachReporter(sparkReporter);
       // }
    }
    @Parameters("browser")
    @BeforeClass
    public WebDriver getDriver(String browser){
        //if (driverThreadLocal.get() == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver =new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driverThreadLocal.set(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
            driver.manage().window().maximize();
            driver.get(baseURL);
        //}

        return driverThreadLocal.get();
    }
    int i=0;
    @AfterMethod
    public void testStatus(ITestResult result) throws IOException {
        // After each test, check if it failed
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log failure details
            test.log(Status.FAIL, result.getMethod().getMethodName());
            test.log(Status.FAIL, "Test failed due to: " + result.getThrowable());
            String screenshotPath = captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath, "Test Failure Screenshot");
        }
        if (result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, result.getName());
        }
        if (result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, result.getMethod().getMethodName());
        }
        extent.flush();
    }

    public String captureScreenshot(String methodName) throws IOException {
       // Random random = new Random();
        //int randomNumber = random.nextInt(900) ;
        String filename = "screenshot_" + methodName + i + ".png";
        String directory = System.getProperty("user.dir") + "//screenshots//";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // FileUtils is provided by Apache Commons IO dependency in pom.xml
        FileUtils.copyFile(sourceFile, new File(directory + filename));
        i++;
        System.out.println(i+"screenshot");
        return directory+filename;
    }

    @AfterClass
    public void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
      //  extent.flush();
    }

}
