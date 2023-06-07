package tests;

import org.testng.annotations.Test;
import pages.PlpPage;

public class AmazonAppTests extends BaseSettings
{
    PlpPage plpPage;

    @Test
    public void  verifyHomePageElements()
    {
        homePage.verifyHomePageElements();

        homePage.searchProduct("e");
        homePage.getSuggestionsList("e");

        homePage.searchProduct("ear bud");
        homePage.getSuggestionsList("ear bud");

        plpPage = homePage.openSearchSuggestionProduct();
    }

    @Test
    public void verifyProductFilter()
    {
       plpPage.filerByPriceLowToHigh();

    }


}
