package userActions;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
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
        /*
        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "accessibility id");
        args.put("selector", "scroll(0, 200)");;
        try {
            driver.executeScript("mobile: scroll", args);
        }
        catch (Exception e)
        {
        }
        */

        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endX = startX;
        int endY = (int) (size.height * 0.2);

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence scroll = new Sequence(input, 0);
        scroll.addAction(input.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(input.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), endX, endY));
        scroll.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(scroll));
    }

    protected void swipeLeftToRight()  {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int startY = size.height / 2;
        int endX = (int) (size.width * 0.8);
        int endY = startY;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        waitForSeconds(1);
    }

    protected void swipeRightToLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int startY = size.height / 2;
        int endX = (int) (size.width * 0.2);
        int endY = startY;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        waitForSeconds(1);
    }


    protected void scrollDownToElementById(String elementIdDetails)
    {//Try to Use String input id locator of Element - com.universalstudios.orlandoresort:id/btnCreateAccount

        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "-android uiautomator");
        //args.put("selector", "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\"com.universalstudios.orlandoresort:id/btnCreateAccount\"))");
        args.put("selector", "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\""+elementIdDetails+"\"))");
        args.put("direction", "down");
        driver.executeScript("mobile: scroll", args);
    }

    protected void scrollDownTo(WebElement element)
    {
        boolean isElementDisplayed = false;

        while (!isElementDisplayed) {
            try
            {
                isElementDisplayed = element.isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                // Element not found or stale, indicating it's not yet visible
            }

            if (!isElementDisplayed) {
                scrollDown();
            }
        }
    }

    protected void scrollUpTo(WebElement element)
    {
        boolean isElementDisplayed = false;

        while (!isElementDisplayed) {
            try
            {
                isElementDisplayed = element.isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                // Element not found or stale, indicating it's not yet visible
            }

            if (!isElementDisplayed) {
                scrollUp();
            }
        }
    }
    public void navigateBack()
    {
        waitForSeconds(2);
        driver.navigate().back();
        waitForSeconds(2);
    }


    protected void addLog(String message)
    {
        ReportResources.extentTest.log(Status.INFO, message);

    }

    protected void scrollDownToElementByText(String elementText)
    { //     args.put("selector", "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"July 2023\").instance(0))");

        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "-android uiautomator");
        args.put("selector", "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ elementText +"\").instance(0))");
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

    public void pullToRefresh() {
        Map<String, Object> args = new HashMap<>();
        args.put("strategy", "accessibility id");
        args.put("selector", "scroll(0, 200)");;
        try {
            driver.executeScript("mobile: scroll", args);
        }
        catch (Exception ignored)
        {
        }
    }

    protected void scrollUp()
    {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endX = startX;
        int endY = (int) (size.height * 0.8);

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence scroll = new Sequence(input, 0);
        scroll.addAction(input.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(input.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), endX, endY));
        scroll.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(scroll));
    }

}
