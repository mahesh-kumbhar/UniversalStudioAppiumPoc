package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;
import java.util.List;

import static utilitys.TimeUtility.getNextMonth;

public class HomePage extends UserActions
{

    AppiumDriver driver;

    public HomePage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/toolbar_with_logo_image")
    private WebElement logoUniversalStudio;

    @AndroidFindBy(accessibility ="Profile")
    private WebElement menuProfile;

    @AndroidFindBy(accessibility ="Wallet")
    private WebElement optionWallet;

    @AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc='Buy Tickets']/android.widget.TextView")
    private WebElement optionBuyTickets;
    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Buy Tickets']")
    private WebElement hdrBuyTickets;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Number of days visiting?']/parent::*/following-sibling::*//android.widget.TextView[@text='2']")
    private WebElement NoOfDays;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Adults (10+)']/following-sibling::*[1]//android.widget.ImageView[@resource-id='com.universalstudios.orlandoresort:id/increment_image']")
    private WebElement btnIncrAdult;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Adults (10+)']/following-sibling::*[1]//android.widget.ImageView[@resource-id='com.universalstudios.orlandoresort:id/decrement_image']")
    private WebElement btnDecrAdult;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Children (3-9)']/following-sibling::*[1]//android.widget.ImageView[@resource-id='com.universalstudios.orlandoresort:id/increment_image']")
    private WebElement btnIncrChild;
    @AndroidFindBy(xpath ="//android.widget.Button[@text='Apply']")
    private WebElement btnApply;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/ticket_shopping_select_button")
    private WebElement btnSelect;
    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/btn_extras_select")
    private WebElement btnSelectExtra;


    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/fragment_add_on_progress_add_to_cart")
    private WebElement btnAddToCart;
    @AndroidFindBy(xpath ="//android.widget.Button[@text='ADD TO CART']")
    private WebElement btnAddToCartCaps;
    @AndroidFindBy(xpath ="//android.widget.TextView[@text='CONTINUE']")
    private WebElement btnContinue;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/sku_specific_messaging_confirm")
    private WebElement btnOK;
    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/ticket_shopping_select_button")
    private WebElement btnSelectDate;
    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Your item has been added to your cart.']")
    private WebElement msgItemAddedToCart;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/btnCheckout")
    private WebElement btnCheckout;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Subtotal:']/following-sibling::*[contains(@text, '$')]")
    private WebElement subtotalPrice;


    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Shipping:']/following-sibling::*[contains(@text, '$')]")
    private WebElement shippingPrice;


    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Tax:']/following-sibling::*[contains(@text, '$')]")
    private WebElement taxPrice;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Total:']/following-sibling::*[contains(@text, '$')]")
    private WebElement totalPrice;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Let’s assign your purchases']")
    private WebElement txtLetsAssignPurchase;

    //Purchase Page
    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Adult')]")
    private WebElement optionAdult;

    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Child')]")
    private WebElement optionChild;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/fragment_assign_name_done")
    private WebElement btnDone;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/shopping_continue")
    private WebElement btnContinueAssignPurchase;
    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Card Information']")
    private WebElement txtCardInformation;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/fragment_payment_information_card_number")
    private WebElement ipCardNumber;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Month']")
    private WebElement txtMonth;
    @AndroidFindBy(xpath ="//android.widget.ListView/android.widget.CheckedTextView[@text='05']")
    private WebElement txtExpMonth;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Year']")
    private WebElement txtyear;
    @AndroidFindBy(xpath ="//android.widget.ListView/android.widget.CheckedTextView[@text='2025']")
    private WebElement txtExpYear;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/fragment_payment_information_security_code")
    private WebElement txtCvv;

    @AndroidFindBy(id ="com.universalstudios.orlandoresort:id/profile_info_address")
    private WebElement selectAddress;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='COMPLETE PURCHASE']")
    private WebElement btnCompletePurchase;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Sorry, your card can not be authorized to complete your order. Please enter another number or try a new card. ']")
    private WebElement txtErrorMessage;



    //************************** Tests

    public void BookTickets()
    {
        handleProgressBar();
        waitForElement(optionBuyTickets);
        click(optionBuyTickets,"option Buy Tickets");
        waitForElement(hdrBuyTickets);
        SelectDays(1);

        //Select No of adults
        SelectAdult(1);

        // Select No Children
        SelectChildren(1);

        click(btnApply, "Button 'Apply");

        // Tickets Select Date
        //scrollDown();
        scrollDownTo("com.universalstudios.orlandoresort:id/ticket_shopping_select_button");
        click(btnSelectDate,"Button 'SELECT A DATE");
        SelectDateFromNextMonth(22);

        click(btnContinue,"Button 'Continue");
        handleProgressBar();
        click(btnApply, "Button 'Apply");

        // Express Passs
        waitForSeconds(3);
        scrollDownTo("com.universalstudios.orlandoresort:id/ticket_shopping_select_button");
        click(btnSelect,"Button 'Select");
        handleProgressBar();
        click(btnAddToCart,"Button 'ADD TO CART");
        click(btnOK,"Button 'OK'");

        handleProgressBar();
        longWaitForElement(msgItemAddedToCart);
        waitForSeconds(7);
        click(btnContinue,"Button 'Continue");

     //
       
            /*

                    scrollDown();
                    scrollDownTo("com.universalstudios.orlandoresort:id/btn_extras_select");
                    click(btnSelectExtra,"Button 'SELECT");
            */

        // Extra
        waitForSeconds(3);
        click(btnContinue,"Button 'Continue");

        //Scroll to Bottom of Page
        waitForSeconds(5);
        scrollDown();
        scrollDownTo("com.universalstudios.orlandoresort:id/btnCheckout");


        // VerifyBilling Summary
        System.out.println("Subtotal: "+getPrice(subtotalPrice));
        System.out.println("Shipping: "+getPrice(shippingPrice));
        System.out.println("Tax: "+getPrice(taxPrice));
        System.out.println("Total:"+getPrice(totalPrice));

        // Checkout
        click(btnCheckout, "Button 'CHECKOUT'");
        waitForElement(txtLetsAssignPurchase);

        handleProgressBar();

        addAdultDetails();
        addChildDetails();
        waitForSeconds(5);
        click(btnContinueAssignPurchase,"Button 'CONTINUE");
        handleProgressBar();
        waitForSeconds(5);
        waitForElement(txtCardInformation);

        //verifyInvalidPayment();


    }

    public  void verifyInvalidPayment()
    {
        // *** Payment Method
        sendKeys(ipCardNumber,"Card Number","4111111111111111");
        click(txtMonth,"Month");
        click(txtExpMonth,"Exp Month ");

        click(txtyear,"Month");
        click(txtExpYear,"Exp Month ");

        sendKeys(txtCvv, "CVV Code  ", "1234");

        scrollDown();

        scrollDownToElementByText("//android.widget.Button[@text='COMPLETE PURCHASE']");
        click(btnCompletePurchase,"Button 'COMPLETE PURCHASE");

        waitForElement(txtErrorMessage);
    }
    public void addAdultDetails()
    {
        waitForSeconds(7);
        click(optionAdult,"Option 'Adult'");

        selectCheckboxAll();

        click(btnDone,"Button 'DONE'");
        handleProgressBar();
    }

    public void addChildDetails()
    {
        waitForSeconds(7);
        click(optionChild,"Option 'Child'");

        selectCheckboxAll();

        click(btnDone,"Button 'DONE'");
        handleProgressBar();
    }

    public void selectCheckboxAll()
    {
        waitForSeconds(5);
        List<WebElement> chkboxList = driver.findElements(AppiumBy.xpath("//android.widget.CheckBox"));
        for(WebElement chkElement : chkboxList)
        {
            chkElement.click();
        }
    }

    public String getPrice( WebElement element)
    {
        return element.getText();
    }

    public void SelectDateFromNextMonth(int nextMonthDate)
    {
        waitForSeconds(5);
        scrollToNextMonth();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+nextMonthDate+"']/following-sibling::*[contains(@text, '$')]")).click();

        click(btnAddToCartCaps ,"button 'ADD TO CART");
        longWaitForElement(msgItemAddedToCart);
        waitForSeconds(6);

    }

    public void scrollToNextMonth()
    {
        String NextToNextMonth = getNextMonth();
        scrollDownToElementByText(NextToNextMonth);
    }

    public void SelectChildren(int NoOfChildren)
    {
        for(int i=0;i<NoOfChildren;i++) {
            click(btnIncrChild, "Increase Children");
        }
    }

    public void SelectAdult(int NoOfAdults)
    {
        waitForSeconds(4);
        if(NoOfAdults >2)
        {
            for(int i=2;i<NoOfAdults;i++) {
                click(btnIncrAdult, "Increase Adult");
            }
        }
        else if(NoOfAdults<2)
        {
            for (int j = (2-NoOfAdults); j > 0; j--) {
                click(btnDecrAdult, "Increase Adult");
            }
        }
    }


    public void SelectDays(int NoOfDays)
    {
        try {
            click((WebElement) AppiumBy.xpath("//android.widget.TextView[@text='Number of days visiting?']/parent::*/following-sibling::*//android.widget.TextView[@text='" + NoOfDays + "']"), "option Buy Tickets");
        }
        catch (Exception e)
        {
            WebElement element = driver.findElement(AppiumBy .xpath("//android.widget.TextView[@text='Number of days visiting?']/parent::*/following-sibling::*//android.widget.TextView[@text='" + NoOfDays + "']"));
            element.click();

        }
        waitForElement(hdrBuyTickets);
    }

    public ProfilePage openProfile()
    {
        waitForElement(logoUniversalStudio);
        click(menuProfile,"Menu Profile");
        waitForElement(optionWallet);
        return new ProfilePage(driver);
    }








    /*

    public void openBurgerMenu()
    {
        click(burgerMenu,"Burger Menu");
    }
*/

}
