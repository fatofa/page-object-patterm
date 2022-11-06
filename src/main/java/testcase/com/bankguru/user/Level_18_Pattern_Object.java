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


public class Level_18_Pattern_Object extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    NewCustomerPageObject newCustomerPage;
    MainPageObject mainPage;
    String customerName, genderMale, dateOfBirth, address, city, state, pin, phone, email;

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

        customerName = "Donal Trumps";
        genderMale = "male";
        dateOfBirth = "06-02-1998";
        address = "35 Address";
        city = "New York";
        state = "California";
        pin = "569985";
        phone = "08777855585";
        email = "hoavd" + randomNumber() + "@gmail.com";
    }

    @Test
    public void TC_01_Register_To_System() {
        logger.info("Register - Step 01: Input to Email texbox");
        emailRegister = "hoavd" + randomNumber() + "@gmail.com";
        registerPage.inputToTextBoxByAttribute(driver, "emailid" , emailRegister);

        logger.info("Register - Step 02: Click to Submit button");
        registerPage.clickToButtonByValue(driver, "Submit");

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

        loginPage.inputToTextBoxByAttribute(driver, "uid" , userIDValue);
        loginPage.inputToTextBoxByAttribute(driver, "password" , passwordValue);

        logger.info("Login - Step 04: Click to Login button");
        loginPage.clickToButtonByValue(driver, "LOGIN");
        mainPage = PageGeneratorManager.getMainPage(driver);

        // Pass
        logger.info("Login - Step 05: Verify home page");
        verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_New_Customer() {
        logger.info("New Customer - Step 01: Open New Customer Page");
        mainPage.openMenuPageByName(driver, "New Customer");
        newCustomerPage = PageGeneratorManager.getCustomerPage(driver);

        logger.info("New Customer - Step 02: Verify New Customer Page Display");
        newCustomerPage.isPageHeaderDisabledByText(driver, "Add New Customer");

        logger.info("New Customer - Step 03: Input to Customer Name text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "name" , customerName);

        logger.info("New Customer - Step 04: Input to Gender with male values");
        newCustomerPage.clickToRadioButtonByValue(driver, "m");

        logger.info("New Customer - Step 05: Input to Date of Birth text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "dob" , dateOfBirth);

        logger.info("New Customer - Step 06: Input to Address area");
        newCustomerPage.inputToTextAreaByAttribute(driver, "addr" , address);

        logger.info("New Customer - Step 07: Input to City text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "city" , city);

        logger.info("New Customer - Step 08: Input to State text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "state" , state);

        logger.info("New Customer - Step 09: Input to PIN text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "pinno" , pin);

        logger.info("New Customer - Step 10: Input to Phone Number text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "telephoneno" , phone);

        logger.info("New Customer - Step 11: Input to Email text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "emailid" , email);

        logger.info("New Customer - Step 12: Input to Password text box");
        newCustomerPage.inputToTextBoxByAttribute(driver, "password" , passwordValue);

        logger.info("New Customer - Step 13: Click to Submit Button");
        newCustomerPage.clickToButtonByValue(driver, "Submit");

        logger.info("New Customer - Step 14: Verify New Customer register success");
        newCustomerPage.isPageHeaderDisabledByText(driver, "Customer Registered Successfully!!!");

        logger.info("New Customer - Step 15: Verify Customer Name display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Customer Name") , customerName);

        logger.info("New Customer - Step 16: Verify Gender display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Gender") , genderMale);

        logger.info("New Customer - Step 17: Verify Date Of Birth display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Birthdate") , dateOfBirth);

        logger.info("New Customer - Step 18: Verify Address display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Address") , address);

        logger.info("New Customer - Step 19: Verify City display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "City") , city);

        logger.info("New Customer - Step 20: Verify State display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "State") , state);

        logger.info("New Customer - Step 21: Verify PIN display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Pin") , pin);

        logger.info("New Customer - Step 22: Verify Mobile No. display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Mobile No.") , phone);

        logger.info("New Customer - Step 23: Verify Email display correct");
        verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Email") , email);
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
