package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.*;
import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;


public class Level_19_Dependency_Test extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl, emailRegister;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    NewCustomerPageObject newCustomerPage;
    MainPageObject mainPage;
    String customerName, genderMale, dateOfBirth, address, city, state, pin, phone, email;

    @BeforeClass
    public void beforeClass() {
        // login at here
    }

    @Test
    public void TC_01_New_Customer() {
        //get id account
        System.out.println("TC_01_New_Customer");
    }

    @Test(dependsOnMethods = "TC_01_New_Customer")
    public void TC_02_Edit_Customer() {
        // using id account
        System.out.println("TC_02_Edit_Customer");
    }

    @Test(dependsOnMethods = "TC_01_New_Customer")
    public void TC_03_New_Account() {
        // using id account
        System.out.println("TC_03_New_Account");
    }

    @Test(dependsOnMethods = "TC_03_New_Account")
    public void TC_04_Edit_Account() {

        System.out.println("TC_04_Edit_Account");
    }

    @Test(dependsOnMethods = {"TC_01_New_Customer" , "TC_03_New_Account"})
    public void TC_05_Deposit() {
        System.out.println("TC_05_Deposit");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

    }
}
