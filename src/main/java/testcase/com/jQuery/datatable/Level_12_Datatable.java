package testcase.com.jQuery.datatable;

import actions.commons.AbstractTest;
import actions.pageObject.jQuery.HomePageObject;
import actions.pageObject.jQuery.PageGeneratorManager;
import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Datatable extends AbstractTest {
	WebDriver driver;
	HomePageObject homePage;
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		System.out.println("Pre-Condition - Open browser and navigate to app Url");
		
		driver = getBrowserDriver(browserName,appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
		
	public void TC_01_Input() throws InterruptedException {
		// Input to text box
		homePage.inputTextToTextboxByColumnName("Females", "15999");
		homePage.inputTextToTextboxByColumnName("Country", "Antigua and Barbuda");
		homePage.inputTextToTextboxByColumnName("Males", "12599691");
		homePage.inputTextToTextboxByColumnName("Total", "553353");
		Thread.sleep(2000);

	}
	
	
	public void TC_02_Navigate() throws InterruptedException {
		homePage.refeshPage(driver);
		// Navigate to any page
		homePage.navigateToPageByPageNumber("6");	
		Thread.sleep(2000);
		
		//Verify page navigated success
		Assert.assertTrue(homePage.isPageActivedByPageNumber("6"));
		
		
		homePage.navigateToPageByPageNumber("9");	
		Thread.sleep(2000);
		Assert.assertTrue(homePage.isPageActivedByPageNumber("9"));
		
		homePage.navigateToPageByPageNumber("4");	
		Thread.sleep(2000);
		Assert.assertTrue(homePage.isPageActivedByPageNumber("4"));
		
		homePage.navigateToPageByPageNumber("5");
		Thread.sleep(2000);
		Assert.assertTrue(homePage.isPageActivedByPageNumber("5"));
	}
	
	public void TC_03_Icon() throws InterruptedException {
		homePage.refeshPage(driver);
		// Delete rows by Country Name
		homePage.clickToDeleteIconByCountryName("AFRICA");
		Thread.sleep(2000);
		homePage.clickToDeleteIconByCountryName("Angola");
		Thread.sleep(2000);
		homePage.clickToDeleteIconByCountryName("Arab Rep of Egypt");
		
		homePage.refeshPage(driver);
		// Edit rows by Country Name
		homePage.clickToEditIconByCountryName("AFRICA");
		Thread.sleep(2000);
		homePage.refeshPage(driver);
		homePage.clickToEditIconByCountryName("Angola");
		Thread.sleep(2000);
		homePage.refeshPage(driver);
		homePage.clickToEditIconByCountryName("Arab Rep of Egypt");
		Thread.sleep(2000);
	}
	
	public void TC_04_Display() throws InterruptedException {
		homePage.refeshPage(driver);
		Assert.assertTrue(homePage.isAllInforDisplay("764956", "Arab Rep of Egypt", "802948", "1567904"));
		Thread.sleep(2000);
		Assert.assertTrue(homePage.isAllInforDisplay("15999", "Armenia", "16456", "32487"));
	}
	
	@Test
	public void TC_04_Index() throws InterruptedException { 
		homePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		homePage.inputTextToTextboxByColumnNameAtRowNumber("Artist", "1" , "HoaVD");
		homePage.inputTextToTextboxByColumnNameAtRowNumber("Album", "1" , "HoaVD223");
		homePage.inputTextToTextboxByColumnNameAtRowNumber("Year", "1" , "HoaVDrrr");
		Thread.sleep(2000);
		
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
