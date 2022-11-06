package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.*;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;
@Epic("Bank Guru User")
@Feature("Register User")
public class Level_15_Log_Report_HTML extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        logger.info("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);

    }
    @Story("User Story 01 - Register to System")
    @Description("Register to System and Verify register success")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Register");
        logger.info("Register - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();

        logger.info("Register - Step 02: Click to 'here' link");
        registerPage = loginPage.clickToHereLink();

        logger.info("Register - Step 03: Input to Email texbox");
        registerPage.inputToEmailTextbox("hoavd" + randomNumber() + "@gmail.com");

        logger.info("Register - Step 04: Click to Submit button");
        registerPage.clickToSubmitButton();

        logger.info("Register - Step 05: Get UserID/ Password infomation");
        userIDValue = registerPage.getUserIDText();
        passwordValue = registerPage.getPasswordText();
    }
    @Story("User Story 02 - Login to System")
    @Description("Login to System and Verify register success")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_To_System() {
        logger.info("Login - Step 01: Get Login Page Url ");
        loginPage = registerPage.openLoginPage(loginPageUrl);

        // First Failed
        logger.info("Login - Step 02: Open Login Page ");
        verifyTrue(loginPage.isLoginFormDisplayed());

        logger.info("Login - Step 03: Input UserID/ Password texbox");
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox(passwordValue);

        logger.info("Login - Step 04: Click to Login button");
        mainPage = loginPage.clickToLoginButton();

        // Pass
        logger.info("Login - Step 05: Verify home page");
        verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank.scasc");
//
//         //Pass
//        logger.info("Login - Step 06: Verify Login form undisplay ");
//        verifyFalse(mainPage.isLoginFormUndisplay());
    }


    public void TC_03_Logout() throws InterruptedException {
        logger.info("Logout - Step 01: Click 'Log Out' link");
        loginPage = mainPage.clickToLogoutLink(driver);

        logger.info("Logout - Step 02: Verify Login form displayed");
        Assert.assertTrue(loginPage.isLoginFormDisplayed());

    }

    @AfterClass
    public void afterClass() {
        logger.info("Post-Condition - Close browser");
        removeBroserDriver();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
