package pages;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends UserActions
{
    AppiumDriver driver;

    public HomePage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(xpath ="//*[@content-desc='Amazon.in']")
    private WebElement logoAmazonIn;

    @AndroidFindBy(className ="android.widget.EditText")
    private WebElement searchBox;

    @AndroidFindBy(xpath ="//*[@text='Go']")
    private WebElement btnGoToSearch;

    @AndroidFindBy(xpath ="(//*[@resource-id='suggestions2']//*[contains(@class, 'android.widget.Button')])[3]")
    private WebElement searchSuggestedProduct;

    @AndroidFindBy(xpath ="//*[@content-desc='Cart']")
    private WebElement iconCart;

    @AndroidFindBy(xpath ="//*[@resource-id='mobile-ptc-button-celWidget']/android.widget.Button")
    private WebElement btnProceedToCheckout;

    @AndroidFindBy(xpath ="//*[@text='OK']")
    private WebElement btnOK;


    // ************************  Page Level Methods

    public void verifyHomePageElements()
    {
        longWaitForElement (logoAmazonIn);
        clickIfDisplayed(btnOK,"Button 'OK' for 'Running in chrome' Notification");

        isDisplayed (logoAmazonIn,"Logo 'Amazon In' on Home page");
        isDisplayed(searchBox,"Product 'Search Box' on Home Page");
        addLog("Home Page variables verified successfully ");
    }
    public void searchProduct(String productName)
    {
        waitForElement(searchBox);
        sendKeys(searchBox,"text in Search box starting with ",productName);
        waitForSeconds(2);
    }

    public void appRefresh()
    {
        waitForSeconds(1);
        scrollUpTo(logoAmazonIn);
        waitForElement(logoAmazonIn);
        click(logoAmazonIn,"Home Logo ");
        waitForSeconds(5);
    }

    public CartPage openCart()
    {
        scrollUpTo(iconCart);
        click(iconCart,"'CART' icon");
        waitForElement(btnProceedToCheckout);
        addLog("CART Summary : " +btnProceedToCheckout.getText().replace("Proceed to Buy",""));
        return new CartPage(driver);
    }





    public void getSuggestionsList (String searchProduct ) {
        boolean searchState=false;
        List<String> suggestions = new ArrayList<>();

        waitForSeconds(2);
        addLog("Verifying below Search Suggestions Starting with : "+searchProduct);
        WebElement suggestionsContainer = driver.findElement(AppiumBy.xpath("//*[@resource-id='suggestions2']") );

        List<WebElement> suggestionElements = suggestionsContainer.findElements( AppiumBy.className("android.widget.Button"));

        for (WebElement suggestionElement : suggestionElements) {
            String suggestionText = suggestionElement.getText();
            if (suggestionText.toLowerCase().startsWith(searchProduct))
            {
                addLog(suggestionText + " starts with '"+searchProduct+"'");
                searchState=true;
            }
            else
            {
                addLog(suggestionText + " does not start with '"+searchProduct+"'");
                searchState=false;
            }

            //System.out.println(suggestionText);
            suggestions.add(suggestionText);
        }
        if(searchState)
            addLog("Search suggestions passed for starting with '"+searchProduct+"' text.");
        else {
            addLog("Search suggestions failed for starting with '" + searchProduct + "' text.");
            Assert.isTrue(searchState, "Search suggestions failed for starting with '" + searchProduct + "' text.");
        }


    }

    public PlpPage openSearchSuggestionProduct()
    {
        click(searchSuggestedProduct,searchSuggestedProduct.getText()+" search suggested product");
        return new PlpPage(driver);
    }

    public PlpPage goForSearch()
    {
        click(btnGoToSearch,"button 'GO' for get Search results");
        waitForSeconds(4);
        return new PlpPage(driver);
    }


}
