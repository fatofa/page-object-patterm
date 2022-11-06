package testcase.com.bankguru.user;

import actions.browserFactory.DriverManagerFactory;
import actions.browserFactory.DriverManager;
import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.LoginPageObject;
import actions.pageObject.bankGuru.MainPageObject;
import actions.pageObject.bankGuru.NewCustomerPageObject;
import actions.pageObject.bankGuru.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.util.Random;

public class Level_06_Browser_Driver_Factory extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    DriverManager driverManager;
    String name, dateOfBirth, address, city, states, pin, phone, email, passwordGen, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;
    NewCustomerPageObject newCustomerPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");

        driverManager = DriverManagerFactory.getBrowserDriver(browserName);
        driver = driverManager.getDriver();

//		System.out.println("Run on browser: + " + browserName
//				+ " with: " + Thread.currentThread().getId());

        loginPage = new LoginPageObject(driver);

    }

    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Register - Step 01: Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();


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


    public void TC_03_New_Customer() throws InterruptedException {
        System.out.println("New Customer - Step 01: Open New Customer page");
        mainPage.openNewCustomerPage(driver);
        newCustomerPage = new NewCustomerPageObject(driver);
        loginPage = new LoginPageObject(driver);

        System.out.println("New Customer - Step 02: Input 'Name' textbox");
        newCustomerPage.inputToNameTextbox("Jame Troy");

        System.out.println("New Customer - Step 03: Input 'Date Of Birth' textbox");
        newCustomerPage.inputToDateOfBirthTextbox("08/10/1996");

        System.out.println("New Customer - Step 04: Input 'Address' textbox");
        newCustomerPage.inputToAddressTextbox("28 CO");

        System.out.println("New Customer - Step 05: Input 'City' textbox");
        newCustomerPage.inputToCityTextbox("California");

        System.out.println("New Customer - Step 06: Input 'States' textbox");
        newCustomerPage.inputToStateTextbox("New York");

        System.out.println("New Customer - Step 07: Input 'Pin' textbox");
        newCustomerPage.inputToPinTextbox("587869");

        System.out.println("New Customer - Step 08: Input 'Phone' textbox");
        newCustomerPage.inputToPhoneTextbox("0978855520");

        System.out.println("New Customer - Step 09: Input 'Email' textbox");
        newCustomerPage.inputToEmailTextbox("jame22" + randomNumber() + "@hotmail.com");

        System.out.println("New Customer - Step 10: Input 'Password' textbox");
        newCustomerPage.inputToPasswordTextbox("Hoavd12345.");

        System.out.println("New Customer - Step 11: Click to 'Submit' button");
        newCustomerPage.clickToSubmitButton();

        System.out.println("New Customer - Step 12: Verify Success message display");
        Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
    }


    public void TC_04_Logout() throws InterruptedException {
        System.out.println("Logout - Step 01: Click 'Log Out' link");
        newCustomerPage.clickToLogoutLink(driver);
        loginPage = new LoginPageObject(driver);

        System.out.println("Logout - Step 02: Verify Login form displayed");
        Assert.assertTrue(loginPage.isLoginFormDisplayed());

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
