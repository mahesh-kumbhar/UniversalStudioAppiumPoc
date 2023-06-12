package pages;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userActions.UserActions;

import java.time.Duration;

public class CheckoutPage extends UserActions
{
    AppiumDriver driver;

    public CheckoutPage(AppiumDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    @AndroidFindBy(xpath ="//*[@resource-id='ap_email_login']")
    private WebElement ipUserName;

    @AndroidFindBy(xpath ="//*[@resource-id='continue']")
    private WebElement btnContinue;

    @AndroidFindBy(xpath ="//*[@text='Continue']")
    private WebElement btnContinuePayment;

    @AndroidFindBy(xpath ="//*[@resource-id='ap_password']")
    private WebElement ipPassword;
    @AndroidFindBy(xpath ="//*[@resource-id='signInSubmit']")
    private WebElement btnSignIn;
    @AndroidFindBy(xpath ="//*[@text='Deliver to this address']")
    private WebElement btnDeliverToThisAdd;

    @AndroidFindBy(xpath ="//*[@text='Select a payment method']")
    private WebElement txtSelectPaymentMethod;
    @AndroidFindBy(xpath ="//*[@text='Pay with Debit/Credit/ATM Cards']")
    private WebElement cardPayment;
    @AndroidFindBy(xpath ="//*[@text='Enter card details']")
    private WebElement txtCardDetails;

    @AndroidFindBy(xpath ="//*[@hint='Card number']")
    private WebElement ipCardDetails;
    @AndroidFindBy(xpath ="//*[@resource-id='add-credit-card-expiry-date-input-id']//*[@text='2023']")
    private WebElement ipEpxYear;

    @AndroidFindBy(xpath ="//android.widget.ListView/*[@text='2024']")
    private WebElement ipNextYear;

    @AndroidFindBy(xpath ="//*[@resource-id='add-credit-card-expiry-date-input-id']/following-sibling::*/child::node()[@text='Enter card details']")
    private WebElement btnEnterCardDetails;
    @AndroidFindBy(xpath ="//*[@text='No, thanks']")
    private WebElement btnNoThanks;
    @AndroidFindBy(xpath ="//*[@hint ='Enter CVV']")
    private WebElement ipCvv;
    @AndroidFindBy(xpath ="//*[@text='Order now']")
    private WebElement txtOrderNow;

    @AndroidFindBy(xpath ="//*[@text='Place Your Order and Pay']")
    private WebElement btnPlaceYourOrderAndPay;



    //********************** Test Methods

    public void selectPayment(String cardNumber,String cardCvv)
    {
        if(isDisplayed(cardPayment))
        {
            click(cardPayment, "Pay with  Debit/Credit/ATM Card");

            click(txtCardDetails, "Card Number to open detailed page");
            sendKeys(ipCardDetails, "Card Number", cardNumber);
            click(ipEpxYear, "Open Expire Year");
            click(ipNextYear, "Next Year By Default");
            click(btnEnterCardDetails, "Button 'Enter card details' to complete this form");
            click(btnNoThanks, "Button 'No Thank' to store card details");
            scrollUp();
        }
        sendKeys(ipCvv,"Card CVV details",cardCvv);
        click(btnContinuePayment,"Button 'Continue with Payment details");
        waitForElement(txtOrderNow);
        isDisplayed(btnPlaceYourOrderAndPay,"Button 'Place Your Order and Pay' to proceed with Order");

        //Assert.isTrue(txtSelectPaymentMethod.isDisplayed(),"Failed to place Order With Incorrect Payment Details.");
    }

    public void selectAddress()
    {
        click(btnDeliverToThisAdd,"button 'Select This Address' to select default address");
        waitForElement(txtSelectPaymentMethod);
        addLog("Default address selected successfully");
    }
    public void login(String userName, String password)
    {
        sendKeys(ipUserName,"Email id",userName);
        click(btnContinue,"button 'Continue'");
        waitForElement(ipPassword);
        ipPassword.sendKeys(password);
        click(btnSignIn,"button 'Sign In'");
        waitForElement(btnDeliverToThisAdd);
        addLog("Logged into account successfully");
    }



}
