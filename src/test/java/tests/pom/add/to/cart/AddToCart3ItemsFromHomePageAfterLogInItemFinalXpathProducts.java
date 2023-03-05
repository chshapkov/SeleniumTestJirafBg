package tests.pom.add.to.cart;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyProfilePage;
import pages.ProductPage;

public class AddToCart3ItemsFromHomePageAfterLogInItemFinalXpathProducts extends TestUtil {
    @Test(dataProvider = "correctCredentials")

    public void addToCart3ItemsFromHomePageAfterLogInItemFinalXpathProducts (String email, String password) {

        //подреденият вариант с final XPath:
        // (не можем да сложим поръчаме директно от хоум пейдж, без да отворим продукта. За това инициализираме
        // не само продукта в хоум пейдж, а и продуктовата страница)

        HomePage homePage = new HomePage(driver);
        LogInPage logInPage = new LogInPage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        HomePage homePageItem1 = new HomePage(driver);
        HomePage homePageItem2 = new HomePage(driver);
        HomePage homePageItem3 = new HomePage(driver);

        ProductPage item1 = new ProductPage(driver);
        ProductPage item2 = new ProductPage(driver);
        ProductPage item3 = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login(email, password);
        myProfilePage.goToHomePage();

        //item1:
        homePageItem1.selectItemFromHomePageParam("[1]/div/div[2]/h3");
        item1.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item1.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");


        //item2:
        homePageItem2.selectItemFromHomePageParam("[2]/div/div[2]/h3/a");
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item2.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item3:
        homePageItem3.selectItemFromHomePageParam("[7]/div/div[2]/h3/a");  //да се замени при липса на наличност с: "[3]"
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");
    }
}
