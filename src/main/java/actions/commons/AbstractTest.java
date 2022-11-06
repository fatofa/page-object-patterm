package actions.commons;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.*;

public abstract class AbstractTest {
//	private WebDriver driver;
//	protected static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	private String projectPath = System.getProperty("user.dir");

	protected static final Logger logger = LogHelper.getLogger();

//	protected AbstractTest() {
//		logger = LogFactory.getLog(getClass());
//	}

	public WebDriver getDriver() {
		return threadDriver.get();
	}
	protected enum Browser {
		CHROME, FIREFOX, CHROMEHEADLESS, FIREFOXHEADLESS;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
	Browser browser = Browser.valueOf(browserName.toUpperCase());
		
		System.out.println("Browser Name = " + browserName);
		
		switch (browser) {
		case CHROME:
			//System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			File path = new File("browserExtension/extension_2_0_12_0.crx");
			ChromeOptions chromeOptionEx = new ChromeOptions();
			chromeOptionEx.addExtensions(path);
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");

			threadDriver.set(new ChromeDriver(chromeOptionEx));
			break;
		case FIREFOX:
			//System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			threadDriver.set(new FirefoxDriver());
			break;
		case CHROMEHEADLESS:
			//System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("-headless");
			chromeOptions.addArguments("window-size=1920x1080");
			threadDriver.set(new ChromeDriver(chromeOptions));
			break;
		case FIREFOXHEADLESS:
			//System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			firefoxOptions.addArguments("window-size=1920x1080");
			threadDriver.set(new FirefoxDriver(firefoxOptions));
			break;
		default:
			throw new RuntimeException("Please choose browser name!");
		}
		
		threadDriver.get().navigate().to(appUrl);
		threadDriver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		
		return threadDriver.get();
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
	Browser browser = Browser.valueOf(browserName.toUpperCase());
		
		System.out.println("Browser Name = " + browserName);
		
		switch (browser) {
		case CHROME:
			//System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			threadDriver.set(new ChromeDriver());
			break;
		case FIREFOX:
			//System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			threadDriver.set(new FirefoxDriver());
			break;
		case CHROMEHEADLESS:
			//System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("-headless");
			chromeOptions.addArguments("window-size=1920x1080");
			threadDriver.set(new ChromeDriver(chromeOptions));
			break;
		case FIREFOXHEADLESS:
			//System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			firefoxOptions.addArguments("window-size=1920x1080");
			threadDriver.set(new FirefoxDriver(firefoxOptions));
			break;
		default:
			throw new RuntimeException("Please choose browser name!");
		}
		
		threadDriver.get().navigate().to("https://demo.guru99.com/v4");
		threadDriver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		
		return threadDriver.get();
	}
	
	
	protected void removeBroserDriver() {
		WebDriver driver = threadDriver.get();
		threadDriver.remove();

		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			logger.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			logger.info("Driver instance name = " + driverInstanceName);

			// Quit driver file
			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				// Quit driver
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			if (condition == true) {
				logger.info(" -------------------------- PASSED -------------------------- ");
			} else {
				logger.info(" -------------------------- FAILED -------------------------- ");
			}
		} catch (Throwable e) {
			//e.printStackTrace();
			System.out.println("false");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				logger.info(" -------------------------- PASSED -------------------------- ");
			} else {
				logger.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			logger.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			//e.printStackTrace();
			pass = false;
			logger.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				logger.info(" -------------- " + logging.getLevel().toString() + " -------------- \n" + logging.getMessage());
			}
		}
	}

}
