package tests;

import org.testng.annotations.Test;

import java.util.List;

public class AmazonAppTests extends BaseSettings
{

    @Test
    public void  verifyHomePageElements()
    {
        homePage.verifyHomePageElements();

        homePage.searchProduct("e");
        homePage.getSuggestionsList("e");

        homePage.searchProduct("ear bud");
        homePage.getSuggestionsList("ear bud");
    }

}
