package testcase.com.bankguru.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class Level_01_Repeat_Yourself {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String userIDValue, passwordValue, loginPageUrl;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/v4/index.php");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Register_To_System() {
        System.out.println("Step 01: Get Login Page Url");
        loginPageUrl = driver.getCurrentUrl();

        System.out.println("Step 02: Click to 'here' link");
        driver.findElement(By.xpath("//a[text()='here']")).click();

        System.out.println("Step 03: Input to Email texbox");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("hellovn@gmail.com");

        System.out.println("Step 04: Click to Submit button");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        System.out.println("Step 05: Get UserID/ Password infomation");
        userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        passwordValue = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login_To_System() {
        System.out.println("Step 01: Get Login Page Url ");
        driver.get(loginPageUrl);

        System.out.println("Step 02: Input UserID/ Password texbox");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIDValue);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordValue);

        System.out.println("Step 03: Click to Login button");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        System.out.println("Step 04: Verify home page");
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}
