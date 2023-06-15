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
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(xpath ="//*[@text='Delete']")
    private WebElement btnDelete;
    @AndroidFindBy(xpath ="//*[@resource-id='mobile-ptc-button-celWidget']/android.widget.Button")
    private WebElement btnProceedToCheckout;

    @AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Cart\"]/android.view.View/android.widget.TextView")
    private WebElement cartProdCount;

    @AndroidFindBy(xpath ="//*[@text='Sign in. Already a customer?']")
    private WebElement txtSignInOption;

    @AndroidFindBy(xpath ="//*[@text='+']")
    private WebElement btnPlus;

    @AndroidFindBy(xpath ="//*[@text='-']")
    private WebElement btnMinus;

    // ******************* methods

    public void incrementCount()
    {
        addLog("Verifying add product by '+' button");
        int beforeCount=getProductCount();
        scrollDownTo(btnPlus);
        click(btnPlus,"Button '+' to add one more product count");
        waitForSeconds(2);
        int afterCount=getProductCount();

        boolean incrementState = beforeCount<afterCount;
        if(incrementState)
            addLog("Product count incremented by '+' button successfully. \n Previous Count : " + beforeCount + ". \n   After '+' Total Product Count : " +afterCount);
        else
            Assert.isTrue( incrementState,"Failed to increment product");
    }

    public void decrementCount()
    {
        addLog("Verifying reduce product by '-' button");
        int beforeCount=getProductCount();
        click(btnMinus,"Button '-' to remove product count");
        waitForSeconds(4);
        int afterCount=getProductCount();

        boolean decrementState = beforeCount>afterCount;
        if(decrementState)
            addLog("Product count decremented by '-' button successfully. \n Previous Count : " + beforeCount + ". \n   After '+' Total Product Count : " +afterCount);
        else
            Assert.isTrue( decrementState,"Failed to increment Product \n Previous Count : " + beforeCount + ". \n   After '+' Total Product Count : " +afterCount);
    }

    public CheckoutPage openCheckoutPage()
    {
        click(btnProceedToCheckout,"Button 'Proceed to Buy' to open 'CHECKOUT' page in details.");
        waitForElement(txtSignInOption);
        return new CheckoutPage(driver);
    }

    public void deleteProduct()
    {
        addLog("Verifying delete product by 'delete' button");
        int beforeCount=getProductCount();
        click(btnDelete,"Button 'Delete'");
        waitForSeconds(2);
        int afterCount=getProductCount();

        boolean deleteState = beforeCount>afterCount;
        if(deleteState)
            addLog("Product Deleted Successfully. \n Previous Count : " + beforeCount + ". \n   After Delete Total Product Count : " +afterCount);
        else
            Assert.isTrue( deleteState,"Failed to delete Product");

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
