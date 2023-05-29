package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class SwagLabsProductPage {
    public SwagLabsProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "title")
    public WebElement productsTitle;

    @FindBy(className = ".inventory_list div[class='inventory_item'] div[class='inventory_item_img']")
    public List<WebElement> productsImages;
}
