package testcase.com.saucelab.sort;

import actions.commons.AbstractTest;
import actions.pageObject.saucelab.LoginPageObject;
import actions.pageObject.saucelab.PageGeneratorManager;
import actions.pageObject.saucelab.ProductPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Level_20_Sort_Asc_Desc extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    ProductPageObject productPage;

    @Parameters({"browser" , "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        logger.info("Pre-Condition - Open browser and navigate to app Url");
        driver = getBrowserDriver(browserName, appUrl);

        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.inputToTextBoxUserName(driver, "standard_user");
        loginPage.inputToTextBoxPassword(driver, "secret_sauce");
        loginPage.clickToElementLogin(driver);
    }

    @Test
    public void TC_01_Sort_Name() throws InterruptedException {
        productPage = PageGeneratorManager.getProductPage(driver);

        logger.info("Sort 01 - Step 01: Select 'Name (A to Z)' in Sort dropdown");
        productPage.selectValueInSortDropdown("Price (low to high)");
        Thread.sleep(3000);

        logger.info("Sort 01 - Step 02: Verify Product Name are sorted ascending");
        verifyTrue(productPage.areProductNameSortAscending());



        logger.info("Sort 01 - Step 03: Select 'Name (Z to A)' in Sort dropdown");
        productPage.selectValueInSortDropdown("Name (Z to A)");
        Thread.sleep(3000);

        logger.info("Sort 01 - Step 04: Verify Product Name are sorted descending");
        verifyTrue(productPage.areProductNameSortDescending());
    }


    @Test
    public void TC_02_Sort_Price() throws InterruptedException {
        logger.info("Sort 01 - Step 01: Select 'Price (low to high)' in Sort dropdown");
        productPage.selectValueInSortDropdown("Price (low to high)");
        Thread.sleep(3000);

        logger.info("Sort 01 - Step 02: Verify Product Price are sorted ascending");
        verifyTrue(productPage.areProductPriceSortAscending());

        logger.info("Sort 01 - Step 01: Select 'Price (high to low)' in Sort dropdown");
        productPage.selectValueInSortDropdown("Price (high to low)");
        Thread.sleep(3000);

        logger.info("Sort 01 - Step 02: Verify Product Price are sorted descending");
        verifyTrue(productPage.areProductPriceSortDescending());
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        removeBroserDriver();
    }
}
