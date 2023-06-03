package tests;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.OnBoardingPage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class BaseSettings
{
    public static AppiumDriver driver;
    //SignInPage signInPage ;
    OnBoardingPage onBoardingPage;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Parameters({"platformName","device","os_version","app","browserstack.user","browserstack.key"}) //-
    @BeforeClass
    public void setUp(String platformName,String deviceName, String osVersion, String app,String bstackUser,String bstackKey) throws IOException {

        // Start Driver As per capability
        startDriver( platformName, deviceName,  osVersion,  app,bstackUser, bstackKey);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //signInPage = new SignInPage(driver);
        onBoardingPage = new OnBoardingPage(driver);
    }

    public void startDriver(String platformName,String deviceName, String osVersion, String app,String bstackUser,String bstackKey) throws IOException
    {
        if (platformName.equalsIgnoreCase("android"))
        {
            // Use Java Client v6.0.0 or above
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("projectName", "UniversalStudioPoc");
            browserstackOptions.put("buildName", "Demo");
            browserstackOptions.put("sessionName", "BookTicket");
            browserstackOptions.put("appiumVersion", "2.0.0");
            capabilities.setCapability("bstack:options", browserstackOptions);
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("platformVersion", "13.0");
            capabilities.setCapability("deviceName", "Google Pixel 7");
            capabilities.setCapability("app", "bs://697b00e88c6e0fff18bf49b8a11278d1ba1bca85");


            URL browserStackUrl = new URL("https://" + bstackUser + ":" + bstackKey + "@hub-cloud.browserstack.com/wd/hub");
            driver = new AppiumDriver(browserStackUrl, capabilities);

        }
        // Set the appropriate capabilities for Android
        if (platformName.equalsIgnoreCase("androidLocal"))
        {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability("appPackage", "com.universalstudios.orlandoresort");
            capabilities.setCapability("appActivity", "com.universalstudios.orlandoresort.controller.userinterface.launcher.LauncherActivity");

            driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities);

        }

        // Set the appropriate capabilities for iOS
        else if (platformName.equalsIgnoreCase("ios"))
        {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
            capabilities.setCapability("appium:udid", "00008030-000C74D10145802E");
            capabilities.setCapability(MobileCapabilityType.APP, "/Users/capgemini/Desktop/BuildReact/FreshDirectReact.ipa");
            capabilities.setCapability("ios:recordScreen", true);

            driver = new IOSDriver(new URL("http://0.0.0.0:4723"), capabilities);

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
