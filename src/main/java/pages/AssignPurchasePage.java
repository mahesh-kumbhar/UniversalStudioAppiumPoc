package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;

public class AssignPurchasePage extends UserActions
{

    AppiumDriver driver;

    public AssignPurchasePage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Adult')]")
    private WebElement optionAdult;

    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Child')]")
    private WebElement optionChild;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/fragment_assign_name_done")
    private WebElement btnDone;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/shopping_continue")
    private WebElement btnContinue;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Card Information']")
    private WebElement txtCardInformation;

    //******************** Methods



}
