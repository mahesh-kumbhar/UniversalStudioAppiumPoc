package tests;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseSettings
{
    public static AppiumDriver driver;
    protected Properties testData;
    HomePage homePage;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Parameters({"device","os_version","app","browserstack.user","browserstack.key","appPackage","appActivity"}) //-
    @BeforeClass
    public void setUp(String deviceName, String osVersion, String app,String bstackUser,String bstackKey,String appPackage,String appActivity) throws IOException {

        // Start Driver As per capability
        startDriver( deviceName,  osVersion,  app,bstackUser, bstackKey,appPackage,appActivity);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create Properties Object for Test Data
        testData = new Properties();
        try
        {
            FileInputStream inputStream= new FileInputStream("src/main/resources/test-data.properties");
            testData.load(inputStream);
            inputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create First Page Instance
        homePage = new HomePage(driver);
    }

    public void startDriver(String deviceName, String osVersion, String app,String bstackUser,String bstackKey,String appPackage, String appActivity) throws IOException
    {

        // Set the appropriate capabilities for Android
        if (app.contains("apk"))
        {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//            capabilities.setCapability(MobileCapabilityType.APP, app);

            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);

           // capabilities.setCapability("noReset", false);  // Do not reset app state between sessions
           //capabilities.setCapability("fullReset", true);  // Do not reinstall the app on every session


            driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities);

            /*
         capabilities.setCapability(MobileCapabilityType.APP, app);
             */

        }

        // Set the appropriate capabilities for iOS
        else if (app.contains(".ipa"))
        {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
            capabilities.setCapability("appium:udid", "00008030-000C74D10145802E");
            capabilities.setCapability(MobileCapabilityType.APP, "/Users/capgemini/Desktop/BuildReact/FreshDirectReact.ipa");
            capabilities.setCapability("ios:recordScreen", true);

            driver = new IOSDriver(new URL("http://0.0.0.0:4723"), capabilities);

        }
        if (app.contains("bs://"))
        {
            // Use Java Client v6.0.0 or above
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("projectName", "Amazon App");
            browserstackOptions.put("buildName", "Demo");
            browserstackOptions.put("sessionName", "Smoke Test");
            browserstackOptions.put("appiumVersion", "2.0.0");
            capabilities.setCapability("bstack:options", browserstackOptions);
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("platformVersion", "10.0");
            capabilities.setCapability("deviceName", "Samsung Galaxy A11");
            capabilities.setCapability("app", app); //"bs://697b00e88c6e0fff18bf49b8a11278d1ba1bca85"
            //capabilities.setCapability("app", "bs://697b00e88c6e0fff18bf49b8a11278d1ba1bca85");


            URL browserStackUrl = new URL("https://" + bstackUser + ":" + bstackKey + "@hub-cloud.browserstack.com/wd/hub");
            driver = new AppiumDriver(browserStackUrl, capabilities);

        }



    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

/*
cd %ANDROID_HOME%/tools
emulator -avd Pixel -no-snapshot-load

appium inspector via its web version?
https://lnkd.in/gDEsSUtC
Make sure to start your appium server with the command
"appium --allow-cors"

 */


}
