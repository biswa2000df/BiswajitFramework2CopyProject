package FRAMEWORK;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserClass {
	
	public static WebDriver driver;
	

	public static void Initialisation(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
			 System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator + "chromedriver.exe");		
			 ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			   option.addArguments("--headless");
			driver = new ChromeDriver(option);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}

		else if (browser.equalsIgnoreCase("edge")) {
//			WebDriverManager.edgedriver().setup();
			EdgeOptions option = new EdgeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(option);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}
		
//		driver.get("https://mail.apmosys.com/webmail/#sign-in");
		
	}

}
