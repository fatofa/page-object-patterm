package testcase.com.bankguru.common;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.LoginPageObject;
import actions.pageObject.bankGuru.MainPageObject;
import actions.pageObject.bankGuru.PageGeneratorManager;
import actions.pageObject.bankGuru.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;

public class Common_02_Register_Login extends AbstractTest {
    WebDriver driver;
    public static String userIDValue, passwordValue;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    String loginPageUrl;

    MainPageObject mainPage;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        logger.info("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        Register_To_System();

        Login_To_System();
    }

    public void Register_To_System() {
        System.out.println("Register - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();

        logger.info("Register - Step 01: Click to 'here' link");
        registerPage = loginPage.clickToHereLink();

        logger.info("Register - Step 02: Input to Email texbox");
        registerPage.inputToEmailTextbox("hoavd" + randomNumber() + "@gmail.com");

        logger.info("Register - Step 03: Click to Submit button");
        registerPage.clickToSubmitButton();

        logger.info("Register - Step 04: Get UserID/ Password infomation");
        userIDValue = registerPage.getUserIDText();
        passwordValue = registerPage.getPasswordText();

    }
    public void Login_To_System() {

        System.out.println("Login - Step 01: Get Login Page Url ");
        loginPage = registerPage.openLoginPage(loginPageUrl);

        System.out.println("Login - Step 02: Open Login Page ");
        verifyTrue(loginPage.isLoginFormDisplayed());

        System.out.println("Login - Step 03: Input UserID/ Password texbox");
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox(passwordValue);

        System.out.println("Login - Step 04: Click to Login button");
        mainPage = loginPage.clickToLoginButton();

        System.out.println("Login - Step 05: Verify home page");
        verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");

        // Get ra Cookie luu lai thanh 1 bien static

        removeBroserDriver();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
