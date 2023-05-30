package scripts;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SwagLabsLoginFormPage;
import pages.SwagLabsProductPage;
import utils.TestData;

public class SwagLabsLoginFormTest extends SwagLabsBase {

    @BeforeMethod
    public void setPage() {
        swagLabsLoginFormPage = new SwagLabsLoginFormPage();
        swagLabsProductPage = new SwagLabsProductPage();
    }

        /*
        Test Case 1: Validate Swag Labs Login Form card
        Given user is on https://www.saucedemo.com/
        Then user should see a logo "Swag Labs"
        And user should see username input box
        And user should see "Username" text in the username input box
        And user should see password input box
        And user should see "Password" text in the password input box
        And user should see LOGIN button
        */

    @Test(priority = 1, description = "Validate Swag Labs Login Form card")
    public void validateLoginFormCard() {

        Assert.assertTrue(swagLabsLoginFormPage.logo.isDisplayed());
        Assert.assertTrue(swagLabsLoginFormPage.usernameInputBox.isDisplayed());
        Assert.assertEquals(swagLabsLoginFormPage.usernameInputBox.getAttribute("placeholder"), TestData.attributeUsername);
        Assert.assertTrue(swagLabsLoginFormPage.passwordInputBox.isDisplayed());
        Assert.assertEquals(swagLabsLoginFormPage.passwordInputBox.getAttribute("placeholder"), TestData.attributePassword);
        Assert.assertTrue(swagLabsLoginFormPage.loginButton.isDisplayed());
        Assert.assertTrue(swagLabsLoginFormPage.loginButton.isEnabled());
    }

        /*
        Test Case 2: Validate login with valid credentials
        Given user is on https://www.saucedemo.com/
        And user enters username as "<username>" and password as "<password>"
        When user clicks on "LOGIN" button
        Then user should be navigated to "https://www.saucedemo.com/inventory.html"
        And user should see "Products" title
        And user should see images of the products in the page

        Examples:
        | username                | password     |
        | "standard_user"         |"secret_sauce"|
        |"problem_user"           |"secret_sauce"|
        |"performance_glitch_user"|"secret_sauce"|
        */

    @DataProvider
    public Object[][] validCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "validCredentials",
            priority = 2,
            description = "Validate Swag Labs Login Form card with valid credentials")
    public void validateLoginWithValidCredentials(String username, String password) {

        swagLabsLoginFormPage.usernameInputBox.sendKeys(username);
        swagLabsLoginFormPage.passwordInputBox.sendKeys(password);
        swagLabsLoginFormPage.loginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), TestData.correctURL);
        Assert.assertEquals(swagLabsProductPage.productsTitle.getText(), TestData.title);
        for (WebElement image : swagLabsProductPage.productsImages) {
            Assert.assertTrue(image.isDisplayed());
        }
        Assert.assertEquals(swagLabsProductPage.productsImages.size(), TestData.expectedImagesAmount);
    }

        /*
        Test Case 2: Validate login with invalid credentials
        And user enters username as "<username>" and password as "<password>"
        When user clicks on "LOGIN" button
        Then user should see the error message "<errorMessage>"

        Examples:
        | username        | password       | errorMessage                                                                |
        |"locked_out_user"|"secret_sauce"  |"Epic sadface: Sorry, this user has been locked out."                        |
        | ""              | ""             | "Epic sadface: Username is required"                                        |
        | "standard_user" | " "            | "Epic sadface: Username and password do not match any user in this service" |
        | " "             | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
        | "1234abc"       | "helloWorld"   | "Epic sadface: Username and password do not match any user in this service" |
         */
    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", " ", "Epic sadface: Username and password do not match any user in this service"},
                {" ", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"1234abc", "helloWorld", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "invalidCredentials",
            priority = 3,
            description = "Validate Swag Labs Login Form card with invalid credentials")
    public void validateLoginFormCard(String username, String password, String errorMessage) {

        swagLabsLoginFormPage.usernameInputBox.sendKeys(username);
        swagLabsLoginFormPage.passwordInputBox.sendKeys(password);
        swagLabsLoginFormPage.loginButton.click();
        Assert.assertTrue(swagLabsLoginFormPage.errorMessage.isDisplayed());
        Assert.assertEquals(swagLabsLoginFormPage.errorMessage.getText(), errorMessage);
    }
}
