package actions.browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxHeadlessDriverManager extends DriverManager {
	private String projectPath = System.getProperty("user.dir");
	@Override
	protected void createDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("-headless");
		firefoxOptions.addArguments("window-size=1920x1080");
		driver = new FirefoxDriver(firefoxOptions);
	}

}
