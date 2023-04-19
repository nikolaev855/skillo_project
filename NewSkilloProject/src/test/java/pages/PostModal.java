package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage {
@FindBy(className = "post-user")
    WebElement usernameLink;
@FindBy(tagName = "app-post-modal")
WebElement modalDialog;
    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void waitForDialogToAppear(){
        smallWait.until(ExpectedConditions.visibilityOf(modalDialog));
    }
    public String getPostUsername(){
        return getElementText(usernameLink);
    }
}
