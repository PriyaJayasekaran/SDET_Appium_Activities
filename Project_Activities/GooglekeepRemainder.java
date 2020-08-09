package SDET_Maven;

import java.net.MalformedURLException;
import java.net.URL;
//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GooglekeepRemainder {
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
    public  void AddRemainder() {
    	wait = new WebDriverWait(driver, 5);
        WebElement AddNote = wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.ImageButton[@resource-id=\"com.google.android.keep:id/new_note_button\"]")));
        AddNote.click();
    	
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("Adding Title");
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Adding Description");
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        MobileElement remainderclick = driver.findElementById("com.google.android.keep:id/menu_switch_to_list_view");
        remainderclick.click();
    	   
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        
        MobileElement dropdown = driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Time - Currently selected - 4:30 PM\"]/android.widget.Spinner");
        dropdown.click();
        
        MobileElement Evening = driver.findElementByXPath("//android.widget.TextView[1][@text ='Evening']");
        Evening.click(); 
        
        
        
       driver.findElementById("com.google.android.keep:id/save").click();
       driver.findElement(MobileBy.AndroidUIAutomator("description(\"Navigate up\")")).click();
       
       String confirmdescription = driver.findElementByXPath("//android.widget.TextView[@text='Today, 6:00 PM']").getText();
       Assert.assertEquals(confirmdescription, "Today, 6:00 PM");
        
    }
    @AfterClass

    public void afterClass() {

        driver.quit();
}
}