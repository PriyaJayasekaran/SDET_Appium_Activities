package SDET_Maven;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Test
public class GoogleChromeToDoList {
    WebDriverWait wait;
    
    AppiumDriver<MobileElement> driver = null;
		
    @BeforeTest
	
    public void setup() throws MalformedURLException {
	 		
        DesiredCapabilities caps = new DesiredCapabilities();	
        caps.setCapability("deviceName", "LG-H910");	
        caps.setCapability("platformName", "Android");	
        caps.setCapability("appPackage", "com.android.chrome");	
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");	
        caps.setCapability("noReset", true);
		        	
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);	
        wait = new WebDriverWait(driver, 10);
	
    }
		
    public void GoogleToDo() {
	       	
       driver.get("https://www.training-support.net/selenium");
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
       wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")));
   	   driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")).click();
   	
   	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	
   	driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollForward(2).scrollIntoView(textStartsWith(\"To-Do List\"))")).click();
   	
   	
   	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Add more tasks to list.");
   	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
   	 
   	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Get number of tasks");
   	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
   	 
   	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Clear the list");
   	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
   	 
   	   	
  	wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text=\"Add more tasks to list.\"]")));
  	driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add more tasks to list.\"]")).click();
  	
  	wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text=\"Get number of tasks\"]")));  	
 	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Get number of tasks\"]")).click();
 	
 	wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text=\"Clear the list\"]")));
 	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Clear the list\"]")).click();
 	    	 
    //driver.findElement(MobileBy.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView")).click();*/
	 	driver.findElement(MobileBy.xpath("//android.view.View[@text,'Clear List']")).click(); 
    }	
    	
    @AfterTest
	
    public void tearDown() {
	
        driver.quit();
	
    }
	

}
