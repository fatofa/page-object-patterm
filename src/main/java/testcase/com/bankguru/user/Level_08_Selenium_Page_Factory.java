package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageFactory.LoginPageObject;
import actions.pageFactory.MainPageObject;
import actions.pageFactory.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_08_Selenium_Page_Factory extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    String name, dateOfBirth, address, city, states, pin, phone, email, passwordGen, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = new LoginPageObject(driver);

    }

    @Test
    public void TC_01_Register_To_System() throws InterruptedException {
        System.out.println("Register - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();
        System.out.println(loginPageUrl);


        System.out.println("Register - Step 02: Click to 'here' link");
        loginPage.clickToHereLink();
        registerPage = new RegisterPageObject(driver);

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
        registerPage.openLoginPage(loginPageUrl);
        loginPage = new LoginPageObject(driver);

        System.out.println("Login - Step 02: Input UserID/ Password texbox");
        loginPage.inputToUserIDTextbox(userIDValue);
        loginPage.inputToPasswordTextbox(passwordValue);

        System.out.println("Login - Step 03: Click to Login button");
        loginPage.clickToLoginButton();
        mainPage = new MainPageObject(driver);

        System.out.println("Login - Step 04: Verify home page");
        Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");

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
