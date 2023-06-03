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

public class OnBoardingPage extends UserActions
{
    AppiumDriver driver;

    public OnBoardingPage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/scrollView_onboarding_carousel")
    private WebElement onBoardingCarousel;

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/on_boarding_cancel_button")

    private WebElement cancelOnBoardButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Schedule Reminder']")
    private WebElement txtScheduleReminder;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='NO']")
    private WebElement btnNo;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    private WebElement btnContinue;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
    private WebElement btnAllowLocation;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='create account']")
    private WebElement btnCreateAccount;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Already have one? ']")
    private WebElement txtAlreadyHaveAccount;

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/item_view_wallet_footer")
    private WebElement btnSignIn;

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/etEmail")
    private WebElement ipEmail;

    public SignInPage openSignInPage()
    {
        waitForSeconds(5);
        click(btnSignIn,"Button 'Sign In'");
        waitForElement(ipEmail);
        return new SignInPage(driver);
    }
    public CreateAccountPage openCreateAccount()
    {

        click(btnCreateAccount,"Button 'Create Account'");
        waitForElement(txtAlreadyHaveAccount);
        return new CreateAccountPage(driver);
    }
    public HomePage closeOnBoardingScreen()
    {
        click(cancelOnBoardButton,"Close Button For OnBoard Screen");
        waitForElement(txtScheduleReminder);
        click(btnNo,"Button 'No' for Schedule Reminder");
        click(btnContinue, "Button 'CONTINUE'");

        AllowAccess();
        return new HomePage(driver);
    }

    public void AllowAccess()
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


    public void verifyOnBoardingCarouselDisplayed()
    {
        longWaitForElement(onBoardingCarousel);
    }

}
