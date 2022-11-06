package actions.browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {
	private String projectPath = System.getProperty("user.dir");
	@Override
	protected void createDriver() {
		//WebDriverManager.firefoxdriver().setup();	
		System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDriver/geckodriver.exe");
		driver = new FirefoxDriver();
		System.out.println(driver);
	}

}
