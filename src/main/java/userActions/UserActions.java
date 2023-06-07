package userActions;

import com.aventstack.extentreports.Status;
import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class UserActions
{
    AppiumDriver driver;

    protected UserActions(AppiumDriver driver)
    {
        this.driver=driver;
    }
    protected void waitForElement(WebElement element)
    {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void longWaitForElement(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element,String eleName)
    {
        waitForElement(element);
        element.click();
        addLog("Clicked on : " +eleName);
    }

    //This Function will use to verify and assert if not displayed
    protected void isDisplayed(WebElement element,String eleName)
    {
        Assert.isTrue(element.isDisplayed(),("Verified - " + eleName + " is displayed "));
        addLog("Verified - " + eleName + " is displayed ");
    }

    protected void waitForSeconds(int SecondsWait)
    {
        try {
            Thread.sleep(1000 * SecondsWait);
        }
        catch (Exception e){
        }
    }

    protected void clickIfDisplayed(WebElement element,String eleName)
    {
        try {
            if (element.isDisplayed()) {
                element.click();
                addLog("Clicked on : " + eleName);
            }
        }
        catch (Exception e)
        { }
    }

    protected void sendKeys(WebElement element,String eleName,String strVal)
    {
        waitForElement(element);
        element.sendKeys(strVal);
        addLog("Entered : " + eleName + " As : " + strVal);
    }
    /**
     * Scrolls down by a small amount using the mobile: scroll command
     */
    protected void scrollDown() {
        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "accessibility id");
        args.put("selector", "scroll(0, 200)");
        try {
            driver.executeScript("mobile: scroll", args);
        }
        catch (Exception e)
        {
        }
    }


    protected void scrollDownTo(String elementDetails)
    {//Try to Use String input id locator of Element - com.universalstudios.orlandoresort:id/btnCreateAccount

        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "-android uiautomator");
        //args.put("selector", "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\"com.universalstudios.orlandoresort:id/btnCreateAccount\"))");
        args.put("selector", "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\""+elementDetails+"\"))");
        args.put("direction", "down");
        driver.executeScript("mobile: scroll", args);


//        waitForElement(element);
//        addLog("Scrolled To : " + element.getText());
    }

    protected void addLog(String message)
    {
        ReportResources.extentTest.log(Status.INFO, message);

    }

    protected void scrollDownToElementByText(String elementText)
    { //     args.put("selector", "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"July 2023\").instance(0))");

        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "-android uiautomator");
        args.put("selector", "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+ elementText +"\").instance(0))");
        driver.executeScript("mobile: scroll", args);
    }

    protected void handleProgressBar() {
        try {
            // Wait for 2 seconds
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the progress bar is displayed
        try {
            WebElement progressBar = driver.findElement(AppiumBy.xpath("//android.widget.ProgressBar[@resource-id='android:id/progress']"));
            if (progressBar.isDisplayed()) {
                // Wait for the progress bar to disappear
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.xpath("//android.widget.ProgressBar[@resource-id='android:id/progress']")));
            }
        } catch (NoSuchElementException e) {
            System.out.println("Progress bar not found");
        }

        try {
            // Wait for 2 seconds
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
