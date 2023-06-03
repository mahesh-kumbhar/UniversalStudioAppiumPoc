package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userActions.UserActions;

import java.time.Duration;

public class SignInPage extends UserActions
{
    AppiumDriver driver;
    public SignInPage(AppiumDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/etEmail")
    private WebElement ipEmail;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/etPassword")
    private WebElement ipPassword;

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/btnSignIn")
    private WebElement btnSignIn;

    @AndroidFindBy(accessibility = "no")
    private WebElement btnNo;
    @AndroidFindBy(accessibility = "maybe later")
    private WebElement btnMayBeLater;

    @AndroidFindBy(accessibility = "not right now")
    private WebElement btnNotRightNow;
    @AndroidFindBy(accessibility = "continue")
    private WebElement btnContinue;

    @AndroidFindBy(accessibility = "done")
    private WebElement btnDone;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='NO']")
    private WebElement optionNo;

    //****************** Methods

    public HomePage SignIn(String Email, String Password)
    {
        sendKeys(ipEmail,"Email",Email);
        sendKeys(ipPassword,"Password",Password);
        waitForSeconds(2);
        click(btnSignIn,"Button 'Sign In'");
        handleProgressBar();
        longWaitForElement(btnNo);
        clickIfDisplayed(btnNo,"Button 'No' For Tickets");
        clickIfDisplayed(btnMayBeLater,"Button 'MAYBE LATER' For Tickets");
        clickIfDisplayed(btnNotRightNow,"Button 'Not RIGHT NOW' For Payment Method");
        clickIfDisplayed(btnContinue,"Button 'CONTINUE' For Location Service");
        AllowAccess();
        clickIfDisplayed(btnContinue,"Button 'CONTINUE' For Notification");
        clickIfDisplayed(btnDone,"Button 'DONE' For Account Setup");
        clickIfDisplayed(optionNo,"Button 'NO' For Reminder");

        AllowAccess();
        return new HomePage(driver);
    }

    public void AllowAccess()
    {
        // wait for the popup to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // get the text of the popup message
            String message = alert.getText();
            System.out.println("Popup message: " + message);

            // accept the popup (click the "OK" button)
            alert.accept();
        }
        catch (Exception e)
        {
            System.out.println("No Popup Displayed");
        }
    }
}
