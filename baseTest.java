package dharmendhira.rahulshettypages.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import dharmendhira.PageObjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public WebDriver driver;
	protected landingPage lP;
	
	public WebDriver initializeDriver() throws IOException
	{
		Object prop=loadFile();
		String webBrowser=((Properties) prop).getProperty("browser");
		
		if(webBrowser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(webBrowser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver();
			driver=new FirefoxDriver();
		}
		else if(webBrowser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	@BeforeMethod
	public landingPage launchApplication() throws IOException
	{
		Object prop=loadFile();
		String url=((Properties) prop).getProperty("appUrl");
		System.out.println("Updated from github");
		driver=initializeDriver();
		lP=new landingPage(driver);
		lP.goToPage(url);
		System.out.println("Trying to make conflict");
		return lP;
	}

	public Object loadFile() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream globalData=new FileInputStream("C:\\Users\\dharmep\\eclipse-workspace\\rahulshettypages2\\src\\main\\java\\dharmendhira\\resources\\GlobalData.properties");
		prop.load(globalData);
		return prop;
		
	}
	@AfterMethod
	public void exitBrowser()
	{
		driver.quit();
		System.out.println("Browser has been closed");
	}
}
