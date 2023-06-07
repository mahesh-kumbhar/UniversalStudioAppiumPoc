package pages;

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
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(xpath ="//*[@content-desc='Amazon.in']")
    private WebElement logoAmazonIn;

    @AndroidFindBy(className ="android.widget.EditText")
    private WebElement searchBox;

    @AndroidFindBy(xpath ="(//*[@resource-id='suggestions2']//*[contains(@class, 'android.widget.Button')])[3]")
    private WebElement searchSuggestedProduct;

    public void verifyHomePageElements()
    {
        longWaitForElement (logoAmazonIn);
        isDisplayed (logoAmazonIn,"Logo Amazon In");
        isDisplayed(searchBox,"searchBox");
    }
    public void searchProduct(String productName)
    {
        sendKeys(searchBox,"Searchbar",productName);
    }




    public void getSuggestionsList (String searchProduct ) {
        List<String> suggestions = new ArrayList<>();

        waitForSeconds(5);
        System.out.println("*** Verifying Search Suggestions ***");
        WebElement suggestionsContainer = driver.findElement(AppiumBy.xpath("//*[@resource-id='suggestions2']") );

        List<WebElement> suggestionElements = suggestionsContainer.findElements( AppiumBy.className("android.widget.Button"));

        for (WebElement suggestionElement : suggestionElements) {
            String suggestionText = suggestionElement.getText();
            if (suggestionText.toLowerCase().startsWith(searchProduct))
            {
                System.out.println(suggestionText + " starts with '"+searchProduct+"'");
            }
            else
            {
                System.out.println(suggestionText + " does not start with '"+searchProduct+"'");
            }

            //System.out.println(suggestionText);
            suggestions.add(suggestionText);
        }
       System.out.println("*** Verified Search Suggestions Successfully for starting with : "+searchProduct+" ***");
    }

    public PlpPage openSearchSuggestionProduct()
    {
        click(searchSuggestedProduct,"random search suggested product");
        return new PlpPage(driver);
    }

}
