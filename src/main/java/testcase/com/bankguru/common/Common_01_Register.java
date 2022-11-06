package testcase.com.bankguru.common;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.LoginPageObject;
import actions.pageObject.bankGuru.MainPageObject;
import actions.pageObject.bankGuru.PageGeneratorManager;
import actions.pageObject.bankGuru.RegisterPageObject;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class Common_01_Register extends AbstractTest {
    WebDriver driver;
    public static String userIDValue, passwordValue;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        logger.info("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        Register_To_System();
    }

    public void Register_To_System() {

        logger.info("Register - Step 01: Click to 'here' link");
        registerPage = loginPage.clickToHereLink();

        logger.info("Register - Step 02: Input to Email texbox");
        registerPage.inputToEmailTextbox("hoavd" + randomNumber() + "@gmail.com");

        logger.info("Register - Step 03: Click to Submit button");
        registerPage.clickToSubmitButton();

        logger.info("Register - Step 04: Get UserID/ Password infomation");
        userIDValue = registerPage.getUserIDText();
        passwordValue = registerPage.getPasswordText();

        removeBroserDriver();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
