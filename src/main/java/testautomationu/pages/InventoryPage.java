package testautomationu.pages;

import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCard;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement remove;
    @FindBy(id = "react-burger-menu-btn")
    WebElement burguerButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButton;

    @FindBy(xpath = "(//button[@class='btn btn_primary btn_small btn_inventory'])[1]")
    WebElement firstItemButton;

    @FindBy(className = "shopping_cart_badge")
    WebElement itemNumber;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeButton;

    public void logout(){
        burguerButton.click();
        logoutButton.click();
    }

    public void addItem(){
        firstItemButton.click();
    }

    public String getCartItemNumber(){
        return itemNumber.getText();
    }

    public void removeItem(){
        removeButton.click();
    }
}
