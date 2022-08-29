package com.swiggy.SwiggyProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SwiggyApp {
	
	 private AndroidDriver driver;

	 
	@BeforeMethod
	  public void setUp() throws MalformedURLException, Throwable {
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("appium:platformVersion", "11.0");
	    desiredCapabilities.setCapability("appium:deviceName", "sdk_gphone_arm64");
	    desiredCapabilities.setCapability("appium:appPackage", "in.swiggy.android");
	    desiredCapabilities.setCapability("appium:appActivity", "in.swiggy.android.activities.HomeActivity");
	    desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
	    desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
	    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
	    desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

	    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

	    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	    
		  driver.findElementById("in.swiggy.android:id/tv_primary_cta").click();
		  driver.findElementById("com.android.permissioncontroller:id/permission_deny_button").click();
		  driver.findElementById("in.swiggy.android:id/location_description").sendKeys("Delhi");
		  Thread.sleep(5000);
		  driver.findElementByXPath("//android.widget.TextView[@text='Delhi - Jaipur Expressway']").click();
		  driver.findElementByXPath("//android.widget.TextView[@text='CONFIRM LOCATION']").click();
		  driver.findElementById("in.swiggy.android:id/address_annotation_textview").click();
		  
	  }
	 
	  
	  @Test
	  public void ChangingLocation() {
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  driver.findElementById("in.swiggy.android:id/location_pin_icon_imageview").click();
		  driver.findElementById("in.swiggy.android:id/location_description").click();
		  driver.findElementById("in.swiggy.android:id/location_description").sendKeys("Banglore");
		  driver.findElementByXPath("//android.widget.TextView[@text='Bangalore']").click(); 
		  driver.findElementByXPath("//android.widget.TextView[@text='CONFIRM LOCATION']").click();
		  
		  
	  }
	  
	  @Test
	  public void SearchPlusAdd() {
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  driver.findElementById("in.swiggy.android:id/tv_search_dls").click();
		  driver.findElementById("in.swiggy.android:id/et_search_query_v2").sendKeys("Pizza");
		  driver.hideKeyboard();
		  driver.findElementByAccessibilityId("Pizza").click();
		  driver.findElementByXPath("//android.widget.TextView[@text='ADD']").click();
		  driver.findElementByXPath("//android.widget.TextView[@text='Checkout']").click();
		
		  String Checkout =   driver.findElementByXPath("//android.view.View[@text='Almost There']").getText();
		  AssertJUnit.assertEquals(Checkout, "Almost There");
		  
		  
	  }
	  
	  @AfterMethod
	  public void TearDown() throws Throwable {
		  
		  Thread.sleep(5000);
		  driver.quit();
	  }

	

}
