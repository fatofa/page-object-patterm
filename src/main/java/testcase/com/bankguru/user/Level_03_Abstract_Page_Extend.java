package testcase.com.bankguru.user;

import actions.commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Level_03_Abstract_Page_Extend extends AbstractPage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String userIDValue, passwordValue, loginPageUrl;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Pre-Condition - Step 01: Open browser");
        System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");

        driver = new ChromeDriver();

        openUrl(driver, "https://demo.guru99.com/v4/index.php");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Register - Step 01: Get Login Page Url");
        loginPageUrl = getPageCurrenPageUrl(driver);

        System.out.println("Register - Step 02: Click to 'here' link");
        clickToElement(driver, "//a[text()='here']");

        System.out.println("Register - Step 03: Input to Email texbox");
        sendkeyToElement(driver, "//input[@name='emailid']", "hellovn@gmail.com");

        System.out.println("Register - Step 04: Click to Submit button");
        clickToElement(driver, "//input[@name='btnLogin']");

        System.out.println("Register - Step 05: Get UserID/ Password infomation");
        userIDValue = getElementText(driver, "//td[text()='User ID :']/following-sibling::td");
        passwordValue = getElementText(driver, "//td[text()='Password :']/following-sibling::td");
    }

    @Test
    public void TC_02_Login_To_System() {
        System.out.println("Login - Step 01: Get Login Page Url ");
        openUrl(driver, loginPageUrl);

        System.out.println("Login - Step 02: Input UserID/ Password texbox");
        sendkeyToElement(driver, "//input[@name='uid']", userIDValue);
        sendkeyToElement(driver, "//input[@name='password']", passwordValue);

        System.out.println("Login - Step 03: Click to Login button");
        clickToElement(driver, "//input[@name='btnLogin']");

        System.out.println("Login - Step 04: Verify home page");
        Assert.assertEquals(getElementText(driver, "//marquee[@class='heading3']"), "Welcome To Manager's Page of Guru99 Bank");

    }


    @AfterClass
    public void afterClass() {
        System.out.println("Post-Condition - Close browser");
        driver.quit();

    }
}
