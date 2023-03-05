package base;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    public String applicationUrl, browser;
    public int implicitWait;

    @BeforeMethod
    public void setUp() {
        readConfig("src/test/resources/config.properties");
        setupBrowserDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        loadTestUrl(applicationUrl);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    private  void setupBrowserDriver (String browser) {

        switch (browser){
            case "chrome":
                driver = setupChromeDriver();
                break;
            case "firefox":
                driver = setupFirefoxDriverDriver();
                break;
            case "edge":
                driver = setupEdgeDriver();
        }
    }

    private WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private WebDriver setupFirefoxDriverDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private WebDriver setupEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private void readConfig(String filePath) {
        try {
            FileInputStream configFile = new FileInputStream(filePath);
            Properties config =  new Properties();
            config.load(configFile);
            applicationUrl = config.getProperty("url");
            browser = config.getProperty("browser");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadTestUrl(String url) {
        driver.get(url);
    }
    @DataProvider(name = "correctCredentials") //името на DataProvider, който ще използваме
    public static Object[][] readCorrectCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][2]; //все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }

    @DataProvider(name = "wrongCredentials")
    public static Object[][] readWrongCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongCredentials.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2]; //само 2 стойности в scv

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }

    @DataProvider(name = "homePageItems")
    public static Object[][] readHomePageItemsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/homePageItems.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }


    @DataProvider(name = "loginCategoriesItems")
    public static Object[][] readLoginCategoriesItemsCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/loginCategoriesItems.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }

}
