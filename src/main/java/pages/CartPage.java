package pages;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends UserActions
{
    AppiumDriver driver;

    public CartPage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(xpath ="//*[@text='Delete']")
    private WebElement btnDelete;
    @AndroidFindBy(xpath ="//*[@resource-id='mobile-ptc-button-celWidget']/android.widget.Button")
    private WebElement btnProceedToCheckout;

    @AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Cart\"]/android.view.View/android.widget.TextView")
    private WebElement cartProdCount;

    @AndroidFindBy(xpath ="//*[@text='Sign in. Already a customer?']")
    private WebElement txtSignInOption;



    // ******************* methods

    public CheckoutPage openCheckoutPage()
    {
        click(btnProceedToCheckout,"Button 'Proceed to Buy' to open 'CHECKOUT' page in details");
        waitForElement(txtSignInOption);
        return new CheckoutPage(driver);
    }

    public void deleteProduct()
    {
        int beforeCount,afterCount;
        beforeCount=getProductCount();
        click(btnDelete,"Button 'Delete'");
        waitForSeconds(2);
        afterCount=getProductCount();
        boolean deleteState = beforeCount>afterCount;
        Assert.isTrue( deleteState,"Failed to delete Product");
        addLog("Product Deleted Successfully. Previous Count : " + beforeCount + ". After Delete " +afterCount);
    }

    public  int getProductCount()
    {
        String cartCount = cartProdCount.getText();
        return Integer.parseInt(cartCount);
        /*
        String cartCount = cartProdCount.getText();
        Pattern pattern = Pattern.compile("\\((\\d+) item(s)?\\)");
        Matcher matcher = pattern.matcher(cartCount);

        if (matcher.find()) {
            String numberString = matcher.group(1);
            return Integer.parseInt(numberString);
        }

        return 0; // Default value if no item count is found
        */
    }
}
