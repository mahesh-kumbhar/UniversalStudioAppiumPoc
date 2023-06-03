package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;
import utilitys.TimeUtility;

import java.time.Duration;

public class CreateAccountPage extends UserActions
{

    AppiumDriver driver;

    public CreateAccountPage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/etFirstName")
    private WebElement ipFirstName;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/etLastName")
    private WebElement ipLastName;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/etEmail")
    private WebElement ipEmail;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/etPassword")
    private WebElement ipPassword;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/cbUpdates")
    private WebElement cbUpdates;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/cbTermsAndConditions")
    private WebElement cbTermsAndConditions;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Already have one? ']")
    private WebElement txtAlreadyHaveAccount;

    @AndroidFindBy(id = "com.universalstudios.orlandoresort:id/btnCreateAccount")
    private WebElement btnCreateAccount;

    //********************************

    public void enterFirstName(String FirstName)
    {
        sendKeys(ipFirstName,"First Name",FirstName);
    }

    public void enterLastName(String LastName)
    {
        sendKeys(ipLastName,"Last Name",LastName);
    }

    public void enterEmail(String email)
    {
        sendKeys(ipEmail,"Email",email);
    }
    public void enterRandomEmail()
    {//"TestUser"+TimeUtility.getDateTime()+"@getnada.com"
        sendKeys(ipEmail,"Email", "TestUser"+TimeUtility.randomString()+"@getnada.com");
    }
    public void enterPassword(String Password)
    {
        sendKeys(ipPassword,"Password",Password);
    }

    public void ScrollToCreateAccountButton()
    {
        click(txtAlreadyHaveAccount,"Activated Page");
        scrollDown();
       // scrollDownTo("com.universalstudios.orlandoresort:id/btnCreateAccount");
    }

    public void selectCheckboxForUpdatesAndOffers()
    {
        click(cbUpdates,"Checkbox For 'Updates and Special offers'");
    }

    public void selectCheckboxTermsAndPrivacy()
    {
        click(cbTermsAndConditions,"Checkbox For 'Terms of Service & Privacy Policy'");
    }

    public void completeCreateAccount()
    {
        click(btnCreateAccount,"Button 'Create Account'");

        btnCreateAccount.click();

        waitForSeconds(10);

        clickIfDisplayed( btnCreateAccount,"Button 'Create Account'");
        LocationsServices.AllowAccess(driver);

        waitForSeconds(10);
    }


}
