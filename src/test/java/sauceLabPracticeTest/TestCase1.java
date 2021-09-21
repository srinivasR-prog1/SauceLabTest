package sauceLabPracticeTest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestCase1 {

	public WebDriver driver = null;

	public static final String URL = "https://oauth-sri1.as400-9ae6f:ad6d57cd-82bf-43ba-b7f6-3b55df209302@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	@BeforeClass
	@Parameters({ "browser", "version", "platform" })
	public void setUp(String br, String vr, String pf) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("browserName", br);
		cap.setCapability("version", vr);
		cap.setCapability("platform", pf);
		driver = new RemoteWebDriver(new URL(URL), cap);

	}

	@Test
	public void verifyLogin() {		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");	
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		if(driver.getTitle().equals("OrangeHRM"))
		{
		driver.findElement(By.partialLinkText("Welcome")).click();		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

}
