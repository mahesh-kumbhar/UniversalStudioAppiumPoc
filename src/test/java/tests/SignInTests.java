package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;


public class SignInTests extends BaseSettings
{
    SignInPage signInPage;
    HomePage homePage;

    @Test
    public void login()
    {
        signInPage = onBoardingPage.openSignInPage();
    //    homePage = signInPage.SignIn("m1Test@getnada.com","Test123#");//"abc1Test@getnada.com","Test@123#"

    //    homePage.BookTickets();
        //homePage.openProfile();
   }

   @Test
   public void myTest2()
   {
       System.out.println("Yet To Develop Test");
   }

   // @Test
    public void logout()
    {
        //homePage.logout();
    }


}
