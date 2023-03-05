package tests.pom.add.to.cart;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyProfilePage;
import pages.ProductPage;

public class AddToCart3ItemsFromHomePageAfterLogInItemCsv extends TestUtil {

//RESUME: вариант с данни от .csv файл за айтемите (логинът е хардкорнат)
    // (не можем да сложим поръчаме директно от хоум пейдж, без да отворим продукта. За това инициализираме
    // не само продукта в хоум пейдж, а и продуктовата страница)


    @Test(dataProvider = "homePageItems")

    public void addToCart3ItemsFromHomePageAfterLogInItemItemCsv(String xpathItem1, String xpathItem2, String xpathItem3) {

        HomePage homePage = new HomePage(driver); //един page, един обект
        LogInPage logInPage = new LogInPage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);


        HomePage homePageItem1 = new HomePage(driver);
        HomePage homePageItem2 = new HomePage(driver);
        HomePage homePageItem3 = new HomePage(driver);

        ProductPage item1 = new ProductPage(driver);
        ProductPage item2 = new ProductPage(driver);
        ProductPage item3 = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login("qa-test1122@abv.bg", "test1122"); //хардкорнато е
        myProfilePage.goToHomePage();

        //item1:
        homePageItem1.selectItemFromHomePageParam(xpathItem1);
        item1.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item1.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");

        //item2:
        homePageItem2.selectItemFromHomePageParam(xpathItem2);
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item2.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item3:
        homePageItem3.selectItemFromHomePageParam(xpathItem3);
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();

        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");
    }
}
