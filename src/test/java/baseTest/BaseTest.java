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
    protected WebDriver driver;

    static String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeSuite
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get(baseURL);

    }
}
