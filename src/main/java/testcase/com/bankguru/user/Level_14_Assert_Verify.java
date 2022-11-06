package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_14_Assert_Verify extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    String name, dateOfBirth, address, city, states, pin, phone, email, passwordGen, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;
    NewCustomerPageObject newCustomerPage;
    DepositPageObject depositPage;
    EditCustomerPageObject editCustomerPage;
    FundTransferPageObject fundTransferPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);

    }

    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Register - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();

        System.out.println("Register - Step 02: Click to 'here' link");
        registerPage = loginPage.clickToHereLink();

        System.out.println("Register - Step 03: Input to Email texbox");
        registerPage.inputToEmailTextbox("hoavd" + randomNumber() + "@gmail.com");

        System.out.println("Register - Step 04: Click to Submit button");
        registerPage.clickToSubmitButton();

        System.out.println("Register - Step 05: Get UserID/ Password infomation");
        userIDValue = registerPage.getUserIDText();
        passwordValue = registerPage.getPasswordText();
    }

    @Test
    public void TC_02_Login_To_System() {
        System.out.println("Login - Step 01: Get Login Page Url ");
        loginPage = registerPage.openLoginPage(loginPageUrl);

        // First Failed
        System.out.println("Login - Step 02: Open Login Page ");
        verifyTrue(loginPage.isLoginFormDisplayed());

        System.out.println("Login - Step 03: Input UserID/ Password texbox");
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox(passwordValue);

        System.out.println("Login - Step 04: Click to Login button");
        mainPage = loginPage.clickToLoginButton();

        // Second Failed
        System.out.println("Login - Step 05: Verify home page");
        verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank - HoaVD");

        // Pass
        System.out.println("Login - Step 06: Verify Login form undisplay ");
        verifyTrue(mainPage.isLoginFormUndisplay());
    }


    @Test
    public void TC_03_Logout() throws InterruptedException {
        System.out.println("Logout - Step 01: Click 'Log Out' link");
        loginPage = mainPage.clickToLogoutLink(driver);

        System.out.println("Logout - Step 02: Verify Login form displayed");
        Assert.assertTrue(loginPage.isLoginFormDisplayed());

    }

    @AfterClass
    public void afterClass() {
        System.out.println("Post-Condition - Close browser");
        removeBroserDriver();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
