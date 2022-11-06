package testcase.com.bankguru.user;

import actions.commons.AbstractPage;
import actions.pageObject.bankGuru.LoginPageObject;
import actions.pageObject.bankGuru.MainPageObject;
import actions.pageObject.bankGuru.NewCustomerPageObject;
import actions.pageObject.bankGuru.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_04_Page_Object_Pattern extends AbstractPage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String userIDValue, passwordValue, loginPageUrl;
    String name, dateOfBirth, address, city, states, pin, phone, email, passwordGen, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;
    NewCustomerPageObject newCustomerPage;


    @BeforeClass
    public void beforeClass() {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");
        System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.navigate().to("https://demo.guru99.com/v4/index.php");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Data Test
        emailRegister = "hoavd" + randomNumber() + "@gmail.com";
        name = "John Jame";
        dateOfBirth = "08/10/1996";
        address = "28 CO";
        city = "California";
        states = "New York";
        pin = "587869";
        phone = "0978855520";
        email = "jame22" + randomNumber() + "@hotmail.com";
        passwordGen = "Hoavd12345.";
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
        registerPage.inputToEmailTextbox(emailRegister);

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

    @Test
    public void TC_03_New_Customer() throws InterruptedException {
        System.out.println("New Customer - Step 01: Open New Customer page");
        mainPage.openNewCustomerPage(driver);
        newCustomerPage = new NewCustomerPageObject(driver);



        System.out.println("New Customer - Step 02: Input 'Name' textbox");
        newCustomerPage.inputToNameTextbox(name);

        System.out.println("New Customer - Step 03: Input 'Date Of Birth' textbox");
        newCustomerPage.inputToDateOfBirthTextbox(dateOfBirth);

        System.out.println("New Customer - Step 04: Input 'Address' textbox");
        newCustomerPage.inputToAddressTextbox(address);

        System.out.println("New Customer - Step 05: Input 'City' textbox");
        newCustomerPage.inputToCityTextbox(city);

        System.out.println("New Customer - Step 06: Input 'States' textbox");
        newCustomerPage.inputToStateTextbox(states);

        System.out.println("New Customer - Step 07: Input 'Pin' textbox");
        newCustomerPage.inputToPinTextbox(pin);

        System.out.println("New Customer - Step 08: Input 'Phone' textbox");
        newCustomerPage.inputToPhoneTextbox(phone);

        System.out.println("New Customer - Step 09: Input 'Email' textbox");
        newCustomerPage.inputToEmailTextbox(email);

        System.out.println("New Customer - Step 10: Input 'Password' textbox");
        newCustomerPage.inputToPasswordTextbox(passwordGen);

        System.out.println("New Customer - Step 11: Click to 'Submit' button");
        newCustomerPage.clickToSubmitButton();

        System.out.println("New Customer - Step 12: Verify Success message display");
        Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
    }


    public void TC_04_Logout() throws InterruptedException {
        System.out.println("Logout - Step 01: Click 'Log Out' link");
        newCustomerPage.clickToLogoutLinkByJS(driver);
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
