package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_05_Page_Object_Deep_Dive extends AbstractTest {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String userIDValue, passwordValue, loginPageUrl;

    LoginPageObject loginPage;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");
        System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.navigate().to("https://demo.guru99.com/v4/index.php");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        loginPage = new LoginPageObject(driver);
    }

    @Test
    public void TC_01_Login_Empty_User_Password() {
        loginPage.inputToUserIDTextbox("");
        loginPage.inputToPasswordTextbox("");

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_02_Login_Empty_User() {
        loginPage.inputToUserIDTextbox("");
        loginPage.inputToPasswordTextbox(passwordValue);

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_03_Login_Empty_Password() {
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox("");

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_04_Login_Invalid_User() {
        loginPage.inputToUserIDTextbox("asc15@#$%%%$$$");
        loginPage.inputToPasswordTextbox(passwordValue);

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_05_Login_Invalid_Password() {
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox("123");

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_06_Login_Incorrect_Password() {
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox("123456789");

        loginPage.clickToLoginButton();

        // Verify output
    }

    @Test
    public void TC_07_Login_Valid_User_Password() {
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox(passwordValue);

        loginPage.clickToLoginButton();

        // Verify output
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Post-Condition - Close browser");
        driver.quit();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
