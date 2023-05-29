package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class SwagLabsLoginFormPage {

 public SwagLabsLoginFormPage(){
     PageFactory.initElements(Driver.getDriver(), this);
 }

    @FindBy(className = "login_logo")
    public WebElement logo;

    @FindBy(id = "user-name")
    public WebElement usernameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    public WebElement errorMessage;

}
