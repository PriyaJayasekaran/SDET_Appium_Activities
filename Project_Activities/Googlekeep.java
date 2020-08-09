package SDET_Maven;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Googlekeep {
    
	AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;
    
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "LG-H910");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
       caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
       
    }

    @Test
    public  void AddNote() {
    	
    	wait = new WebDriverWait(driver, 5);  	
        WebElement AddNote = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.ImageButton[@resource-id=\"com.google.android.keep:id/new_note_button\"]")));
        AddNote.click();
    	       
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
               
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("Adding Title");
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Adding Description");
        
        driver.findElement(MobileBy.AndroidUIAutomator("description(\"Navigate up\")")).click();
                
        String confirmtitle = driver.findElementByXPath("//android.widget.TextView[1][@text='Adding Title']").getText();
        Assert.assertEquals(confirmtitle, "Adding Title");

        String confirmdescription = driver.findElementByXPath("//android.widget.TextView[2][@text='Adding Description']").getText();
        Assert.assertEquals(confirmdescription, "Adding Description");
    }
    
@AfterClass

public void afterClass() {

    driver.quit();

}
}