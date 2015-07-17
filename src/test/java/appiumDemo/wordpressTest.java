package appiumDemo;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class wordpressTest {
	
	private static final String ERRORTEXT = "We can't log you in";
	public AppiumDriver driver;

	@Before
	public void setUp() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/app");
		File app = new File(appDir, "WordPress.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "");
		capabilities.setCapability("deviceName", "emulator-5554");
		
		capabilities.setCapability("platformVersion", "19");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "org.wordpress.android");
		capabilities.setCapability("appActivity", ".Dashboard");
		capabilities.setCapability("appWaitActivity", ".Dashboard");
		
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testUI() {
		
		driver.findElement(By.id("org.wordpress.android:id/nux_username")).sendKeys("Avtor");
		driver.findElement(By.id("org.wordpress.android:id/nux_password")).sendKeys("avpass");
		driver.findElement(By.id("org.wordpress.android:id/nux_sign_in_button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("org.wordpress.android:id/nux_dialog_title")).getText(), ERRORTEXT);
		
		
		

	}

}
