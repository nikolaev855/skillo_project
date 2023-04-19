package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends BasePage {
    private  WebDriverWait wait;

    @FindBy(id = "homeIcon")
    WebElement homeIcon;

    @FindBy(id = "nav-link-home")
    WebElement homeLink;

    @FindBy(id = "nav-link-login")
    WebElement loginLink;

    @FindBy(linkText = "Profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostLink;
    @FindBy(xpath = "/html/body/app-root/div[1]/app-header/header/nav/div/div/ul[2]/li")
    WebElement logoutButton;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void goToLogin(){
        clickElement(loginLink);
    }
    public void goToHome() {
        clickElement(homeLink);
    }
    public void goToProfile(){
        clickElement(profileLink);
    }
    public void goToNewPost(){
        clickElement(newPostLink);
    }
    public void clickLogout(){
        clickElement(logoutButton);
    }
}
