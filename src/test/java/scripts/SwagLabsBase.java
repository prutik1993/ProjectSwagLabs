package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.SwagLabsLoginFormPage;
import pages.SwagLabsProductPage;
import utils.ConfigReader;
import utils.Driver;

public class SwagLabsBase {

    WebDriver driver;
    SwagLabsLoginFormPage swagLabsLoginFormPage;
    SwagLabsProductPage swagLabsProductPage;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("appURL"));
    }

    @AfterMethod
    public void teardown(){
        Driver.quitDriver();
    }

}
