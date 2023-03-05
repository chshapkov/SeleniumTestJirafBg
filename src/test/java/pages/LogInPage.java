package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BasePage{

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy (name = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement loginBtn;


    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public MyProfilePage login(String email, String password){
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        return new MyProfilePage(driver);
    }
}
