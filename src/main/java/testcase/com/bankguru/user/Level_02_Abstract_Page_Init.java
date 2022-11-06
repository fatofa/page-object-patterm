package testcase.com.bankguru.user;

import actions.commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Level_02_Abstract_Page_Init {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String userIDValue, passwordValue, loginPageUrl;
    AbstractPage abstractPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");

        driver = new ChromeDriver();
        //abstractPage = new AbstractPage();

        abstractPage.openUrl(driver, "https://demo.guru99.com/v4/index.php");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Step 01: Get Login Page Url");
        loginPageUrl = abstractPage.getPageCurrenPageUrl(driver);

        System.out.println("Step 02: Click to 'here' link");
        abstractPage.clickToElement(driver, "//a[text()='here']");

        System.out.println("Step 03: Input to Email texbox");
        abstractPage.sendkeyToElement(driver, "//input[@name='emailid']", "hellovn@gmail.com");

        System.out.println("Step 04: Click to Submit button");
        abstractPage.clickToElement(driver, "//input[@name='btnLogin']");

        System.out.println("Step 05: Get UserID/ Password infomation");
        userIDValue = abstractPage.getElementText(driver, "//td[text()='User ID :']/following-sibling::td");
        passwordValue = abstractPage.getElementText(driver, "//td[text()='Password :']/following-sibling::td");
    }

    @Test
    public void TC_02_Login_To_System() {
        System.out.println("Step 01: Get Login Page Url ");
        abstractPage.openUrl(driver, loginPageUrl);

        System.out.println("Step 02: Input UserID/ Password texbox");
        abstractPage.sendkeyToElement(driver, "//input[@name='uid']", userIDValue);
        abstractPage.sendkeyToElement(driver, "//input[@name='password']", passwordValue);

        System.out.println("Step 03: Click to Login button");
        abstractPage.clickToElement(driver, "//input[@name='btnLogin']");

        System.out.println("Step 04: Verify home page");
        Assert.assertEquals(abstractPage.getElementText(driver, "//marquee[@class='heading3']"), "Welcome To Manager's Page of Guru99 Bank");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}
