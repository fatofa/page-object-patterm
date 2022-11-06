package testcase.com.bankguru.user;

import actions.commons.AbstractTest;
import actions.pageObject.bankGuru.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testcase.com.bankguru.common.Common_01_Register;

import java.util.Random;

public class Level_16_Share_Class_State_01 extends AbstractTest {
    WebDriver driver;
    String userIDValue, passwordValue, loginPageUrl;
    LoginPageObject loginPage;
    MainPageObject mainPage;

    Level_16_Share_Class_State_02 level_16_share_class_state_02 = new Level_16_Share_Class_State_02();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Pre-Condition - Open browser and navigate to app Url");

        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        System.out.println("Pre-Condition - Step 01: Input UserID/ Password text box");
        loginPage.inputToUserIDTextbox(Common_01_Register.userIDValue);
        loginPage.inputToPasswordTextbox(Common_01_Register.passwordValue);

        System.out.println("Login - Step 02: Click to Login button");
        mainPage = loginPage.clickToLoginButton();

    }

    @Test
    public void TC_01_Create_New_Customer() {
  
    }



    @Test
    public void TC_02_View_New_Customer() {

    }

    @Test
    public void TC_03_Edit_New_Customer() {

    }

    @Test
    public void TC_04_Delete_New_Customer() {

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
