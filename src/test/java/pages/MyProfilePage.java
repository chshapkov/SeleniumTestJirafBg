package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends BasePage {

    @FindBy(xpath= "/html/body/main/header/nav/div/div/div[1]/div[2]/div[3]/div/div/button/i")
    private WebElement dropdown;

    @FindBy (xpath = "/html/body/main/header/nav/div/div/div[1]/div[2]/div[3]/div/div/ul/li[2]/a")
    private WebElement dropdownExit;

    @FindBy (xpath = "/html/body/main/div/div/nav/ol/li[1]/a/span")
    private WebElement homePageBtn;

    public MyProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public LogInPage logout(){ //пренасочване към следващата страница (може и:  public void login(String email, String password){}
        dropdown.click();
        dropdownExit.click();
        return new LogInPage(driver);
    }

    public HomePage goToHomePage(){
        homePageBtn.click();
        return new HomePage(driver);
    }
}
