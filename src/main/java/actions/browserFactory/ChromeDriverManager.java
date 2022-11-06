package actions.browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {
	private String projectPath = System.getProperty("user.dir");
	@Override
	protected void createDriver() {
		System.setProperty("webdriver.chrome.driver", projectPath +  "/browserDriver/chromedriver.exe");
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--incognito");
		driver = new ChromeDriver();
	}

}
