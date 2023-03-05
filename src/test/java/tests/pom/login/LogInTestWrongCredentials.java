package tests.pom.login;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTestWrongCredentials extends TestUtil {
    @Test(dataProvider = "wrongCredentials")
    public void loginWithWrongCredentials (String email, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver);
        logInPage.login(email, password);

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/section/section/div/ul/li"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed.");
    }
}
