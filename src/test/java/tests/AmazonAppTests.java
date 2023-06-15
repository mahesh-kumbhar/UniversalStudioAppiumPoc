package tests;

import com.sun.source.tree.AssertTree;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.PlpPage;

public class AmazonAppTests extends BaseSettings
{
    PlpPage plpPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Test(priority = 1)
    public void  verifyHomePageElements()
    {
        homePage.verifyHomePageElements();
    }

   @Test(priority = 2 ,dependsOnMethods = "verifyHomePageElements")
    public void verifySearchAndFilter()
    {
        homePage.searchProduct("m");
        homePage.getSuggestionsList("m");

        homePage.searchProduct("mobile");
        homePage.getSuggestionsList("mobile");

        plpPage = homePage.openSearchSuggestionProduct();
        plpPage.filerByPriceLowToHigh();
    }

    @Test(priority = 3, dependsOnMethods = "verifySearchAndFilter")
    public void verifyProduct() {
        plpPage = new PlpPage(driver);
        plpPage.addProductToCart(2);

        homePage.appRefresh();
        homePage.searchProduct("fruit");
        plpPage= homePage.goForSearch();

        plpPage.addProductToCart(2);
    }

    @Test(priority = 4, dependsOnMethods = "verifyProduct")
    public void verifyCart()
    {
        cartPage= homePage.openCart();
        cartPage.incrementCount();
        cartPage.decrementCount();
        cartPage.deleteProduct();

    }

    @Test(priority = 5, dependsOnMethods = "verifyCart")
    public void validateCheckout()
    {
        checkoutPage= cartPage.openCheckoutPage();
        checkoutPage.login(testData.getProperty("azUsername"),testData.getProperty("azPassword"));
        //checkoutPage.selectAddress();
        checkoutPage.addAddress("Laxmi Sadan Sharad Nagar","411019");
        checkoutPage.selectPayment(testData.getProperty("cardNumber"), testData.getProperty("cardCvv"));
    }




}
