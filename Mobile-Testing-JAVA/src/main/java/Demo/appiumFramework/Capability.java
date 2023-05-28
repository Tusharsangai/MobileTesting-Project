package Demo.appiumFramework;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
//import java.net.URI;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
public class Capability {
       public static String devicename;
       public static String platformname;
       public static String apppackage;
       public static String appactivity;
       public static String chromedriver;
       public  static AppiumDriverLocalService service;
       
       
       public static  AppiumDriverLocalService startserver()
  	{
  boolean flag = checkifserverisRunning(4723);
  if(!flag)
  {
  		service = AppiumDriverLocalService.buildDefaultService();
  }
  		return service;
  		
  	}
//     
      public static boolean checkifserverisRunning(int port)
  	{
  		boolean isServerRunning=false;
  		ServerSocket serversocket;
  		try{
  			serversocket = new ServerSocket(port);
  			serversocket.close();
  		}
  		catch(IOException e)
  		{
  			isServerRunning = true;
  		}
  		finally {
  			serversocket=null;
  		}
  		return isServerRunning;
  	}
      
//      public AppiumDriverLocalService startserver() {
//   	   
//   	   service = AppiumDriverLocalService.buildDefaultService();
//   	   
//   	   return service;
//      }
//       
//       public static void startemulator() throws IOException, InterruptedException {
//    		Runtime.getRuntime().exec("C://Users//LENOVO//Desktop//emulator.bat");
//    	    Thread.sleep(10000);
//       } 
	public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {
		//startemulator();
		FileReader fis = new FileReader(System.getProperty("user.dir")+"//src//main//java//Global.properties");
		Properties ps = new Properties();
		ps.load(fis);
		devicename = ps.getProperty("deviceName");
		platformname = ps.getProperty("platformName");
		apppackage = ps.getProperty("apppackage");
		appactivity = ps.getProperty("appactivity");
		chromedriver = ps.getProperty("chromeExecutable");
		
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
		//below two are added for more info this part is not mandatory   
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		//hybrid capability //driver
		dc.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriver);
		
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		return driver;
  }
}