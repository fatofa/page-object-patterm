package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.*;
import actions.utilities.DataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testdata.com.bankguru.data.BankGuru;

import java.util.Random;


public class Level_22_Data_Test_03_Other_Class extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;
    DataHelper data = new DataHelper();
    NewCustomerPageObject newCustomerPage;
    String emailAddress;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        logger.info("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        logger.info("Pre-Condition - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();

        logger.info("Pre-Condition - Step 02: Click to 'here' link");
        registerPage = loginPage.clickToHereLink();
    }

    @Test
    public void TC_01_Register_To_System() {
        emailAddress = BankGuru.NewCustomer.PR_EMAIL_ADDRESS + randomNumber() + BankGuru.NewCustomer.POST_EMAIL_ADDRESS;
        logger.info("Register - Step 01: Input to Email texbox");
        registerPage.inputToEmailTextbox(emailAddress);

        logger.info("Register - Step 02: Click to Submit button");
        registerPage.clickToSubmitButton();

        logger.info("Register - Step 03: Get UserID/ Password infomation");
        userIDValue = registerPage.getUserIDText();
        passwordValue = registerPage.getPasswordText();
    }

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
        verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_New_Customer() throws InterruptedException {
        logger.info("New Customer - Step 01: Open New Customer Page");
        mainPage.openMenuPageByName(driver, "New Customer");
        newCustomerPage = PageGeneratorManager.getCustomerPage(driver);

        logger.info("New Customer - Step 02: Input to Full Name Text box with: " + BankGuru.NewCustomer.FULLNAME);
        newCustomerPage.inputToTextBoxByAttribute(driver, "name" , BankGuru.NewCustomer.FULLNAME);

        logger.info("New Customer - Step 03: Input to Street Text box with: " + BankGuru.NewCustomer.STREET_ADDRESS);
        newCustomerPage.inputToTextAreaByAttribute(driver, "addr" , BankGuru.NewCustomer.STREET_ADDRESS);

        logger.info("New Customer - Step 04: Input to Phone Text box with:" + BankGuru.NewCustomer.PHONE_NUMBER);
        newCustomerPage.inputToTextBoxByAttribute(driver, "telephoneno" , BankGuru.NewCustomer.PHONE_NUMBER);

        logger.info("New Customer - Step 05: Input to City Text box with: " + BankGuru.NewCustomer.CITY);
        newCustomerPage.inputToTextBoxByAttribute(driver, "city" , BankGuru.NewCustomer.CITY);

        Thread.sleep(5000);
    }


    public void TC_04_Logout() throws InterruptedException {
        logger.info("Logout - Step 01: Click 'Log Out' link");
        loginPage = mainPage.clickToLogoutLink(driver);

        logger.info("Logout - Step 02: Verify Login form displayed");
        Assert.assertTrue(loginPage.isLoginFormDisplayed());

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        logger.info("Post-Condition - Close browser");
        removeBroserDriver();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }

}
