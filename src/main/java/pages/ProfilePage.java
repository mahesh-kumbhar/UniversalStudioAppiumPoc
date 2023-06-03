package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;

public class ProfilePage extends UserActions
{
    AppiumDriver driver;
    public ProfilePage(AppiumDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }

    @AndroidFindBy(xpath ="//android.widget.Button[@text='Create Account']")
    private WebElement btnCreateAccount;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Already have one? ']")
    private WebElement txtAlreadyHaveAccount;


    //****************** Methods
    public CreateAccountPage openCreateAccount()
    {
        click(btnCreateAccount,"Button 'Create Account'");
        waitForElement(txtAlreadyHaveAccount);
        return new CreateAccountPage(driver);
    }
}
