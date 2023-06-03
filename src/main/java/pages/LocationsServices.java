package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocationsServices
{
    public static void AllowAccess(AppiumDriver driver)
    {
        // wait for the popup to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // get the text of the popup message
        String message = alert.getText();
        System.out.println("Popup message: " + message);

        // accept the popup (click the "OK" button)
        alert.accept();

    }
}
