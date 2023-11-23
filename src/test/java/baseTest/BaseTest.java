package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import pageClasses.AdminMenu;
import pageClasses.AdminUserManagement;
import pageClasses.LoginPage;
import pageClasses.MainMenu;

import java.time.Duration;

public class BaseTest {
   // protected static WebDriver driver;

    public static String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = new ChromeDriver();  // You can use other drivers too
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
    /*public void tearDown(){
        driver.quit();
    }*/
}
