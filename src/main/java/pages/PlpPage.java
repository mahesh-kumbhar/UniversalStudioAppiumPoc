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
import java.util.Objects;

public class PlpPage extends UserActions
{
    AppiumDriver driver;

    public PlpPage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(xpath ="//*[@resource-id='s-all-filters']")
    private WebElement btnFilter;

    @AndroidFindBy(xpath ="//*[@text='Sort by']")
    private WebElement optionSortBy;

    @AndroidFindBy(xpath ="//*[@text='Price: Low to High']")
    private WebElement optionSPriceLowToHigh;

    @AndroidFindBy(xpath ="//*[contains(@content-desc, 'Show') and contains(@content-desc, 'results')]")
    private WebElement btnShowResults;

    @AndroidFindBy(xpath ="//*[@text='✕']")
    private WebElement btnX;
    @AndroidFindBy(xpath ="//*[@resource-id='add-to-cart-button']")
    private WebElement btnAddToCart;

    @AndroidFindBy(xpath ="//*[@text='DONE']")
    private WebElement btnDone;

    @AndroidFindBy(xpath ="//*[contains(@text, 'Added to')]" ) //"//*[@text='Added to Cart']"
    private WebElement txtAddedToCart;



    public void addProductToCart(int IndexNumber)
    {
        String productXpath ="(//*[contains(@content-desc,'₹')])["+ IndexNumber+"]";
        try {
            scrollDownTo(driver.findElement(AppiumBy.xpath(productXpath)));
        }
        catch (Exception e)
        {
            scrollDown();
        }
        WebElement product= driver.findElement(AppiumBy.xpath(productXpath));


        click(product,"Product : "+IndexNumber+" to Open in Details");
        //clickIfDisplayed(btnX,"Close Sign in Page Alert");

        waitForSeconds(1);
        swipeRightToLeft();
        swipeRightToLeft();

        swipeLeftToRight();
        swipeLeftToRight();

        scrollDown();
        scrollDownTo(btnAddToCart);
        waitForSeconds(2);
        click(btnAddToCart,"Button 'Add to Cart'");

        waitForElement(txtAddedToCart);


        clickIfDisplayed(btnDone,"Button 'DONE'");

    //  navigateBack();
    //  navigateBack();
    }


    public void filerByPriceLowToHigh()
    {
        click(btnFilter,"Button Filter");
        click(optionSortBy,"option 'Sort by'");
        click(optionSPriceLowToHigh,"Option 'Price: Low to High'");
        click(btnShowResults,"Button Show Result");
        waitForSeconds(5);

        //scrollDownToElementByText("More results");
        scrollDown();

        waitForSeconds(1);

        List<WebElement> elementList = driver.findElements(AppiumBy.xpath("//*[@text='Results']/parent::*/following-sibling::*//*[contains(@content-desc,'Get it by')]/child::*[1]"));

        List<Integer> priceList = new ArrayList<>();

        for(WebElement element : elementList)
        {
            String getPriceValid = element.getText();
            if(!Objects.equals(getPriceValid, ""))
            {
            int price = Integer.parseInt(getPriceValid.replace("₹",""));
            priceList.add(price);
            System.out.println(element.getText());
            }
        }

        boolean isAscendingOrder = isAscending(priceList);
        if(isAscendingOrder)
            addLog("Price is sorted by Low to High");
        else
            addLog("XXX FAILED : Price is not sorted by Low to High");


    }
    public static boolean isAscending(List<Integer> priceList) {
        for (int i = 0; i < priceList.size() - 1; i++) {
            if (priceList.get(i) > priceList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
