package tests.pom.login;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTestCorrectCredentials extends TestUtil {
    @Test(dataProvider = "correctCredentials")

    public void successfulLogin(String email, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver);
//        MyProfilePage myProfilePage = logInPage.login(email, password);
        logInPage.login(email, password);
        WebElement myProfileContent = driver.findElement(By.id("content"));
        Assert.assertTrue(myProfileContent.isDisplayed(), "Мy Profile content was not displayed");
    }
}
