package tests;

import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.ProfilePage;

public class CreateAccountTests extends BaseSettings
{
    // TestUniversal@getnada.com / Test123#
    CreateAccountPage createAccountPage;
    HomePage homePage;
    ProfilePage profilePage;

    @Test
    public void CreateAccountFromOnBoardingScreen()
    {
        createAccountPage=onBoardingPage.openCreateAccount();

        createAccountPage.enterFirstName("Overlay First");
        createAccountPage.enterLastName("Overlay Last");
        createAccountPage.enterRandomEmail();
        createAccountPage.enterPassword("Test123#");
        createAccountPage.ScrollToCreateAccountButton();
        createAccountPage.selectCheckboxForUpdatesAndOffers();
        createAccountPage.selectCheckboxTermsAndPrivacy();
        createAccountPage.completeCreateAccount();
    }





   // @Test
    public void CreateAccountFromProfile()
    {
        onBoardingPage.verifyOnBoardingCarouselDisplayed();
        homePage = onBoardingPage.closeOnBoardingScreen();

        profilePage = homePage.openProfile();

        createAccountPage = profilePage.openCreateAccount();
        createAccountPage.enterFirstName("Profile First");
        createAccountPage.enterLastName("Profile Last");
        createAccountPage.enterRandomEmail();
        createAccountPage.enterPassword("Test123#");
        createAccountPage.ScrollToCreateAccountButton();
        createAccountPage.selectCheckboxForUpdatesAndOffers();
        createAccountPage.selectCheckboxTermsAndPrivacy();
        createAccountPage.completeCreateAccount();

    }
}
