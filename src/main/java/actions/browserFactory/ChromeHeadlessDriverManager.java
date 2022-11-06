package actions.browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessDriverManager extends DriverManager {
	private String projectPath = System.getProperty("user.dir");
	@Override
	protected void createDriver() {
		System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("-headless");
		chromeOptions.addArguments("window-size=1920x1080");
		driver = new ChromeDriver(chromeOptions);
	}

}
