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
        homePage.searchProduct("e");
        homePage.getSuggestionsList("e");

        homePage.searchProduct("ear bud");
        homePage.getSuggestionsList("ear bud");

        plpPage = homePage.openSearchSuggestionProduct();
        plpPage.filerByPriceLowToHigh();
    }

    @Test(priority = 3, dependsOnMethods = "verifySearchAndFilter")
    public void verifyProduct() {
        plpPage = new PlpPage(driver);
        plpPage.addProductToCart(2);

        homePage.appRefresh();
        homePage.searchProduct("mobile");
        plpPage= homePage.goForSearch();

        plpPage.addProductToCart(2);
    }

    @Test(priority = 4, dependsOnMethods = "verifyProduct")
    public void verifyCart()
    {
        cartPage= homePage.openCart();
        cartPage.deleteProduct();
    }

    @Test(priority = 5, dependsOnMethods = "verifyCart")
    public void validateCheckout()
    {
        checkoutPage= cartPage.openCheckoutPage();
        checkoutPage.login(testData.getProperty("azUsername"),testData.getProperty("azPassword"));
        checkoutPage.selectAddress();
        checkoutPage.selectPayment(testData.getProperty("cardNumber"), testData.getProperty("cardCvv"));
    }




}
