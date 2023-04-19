package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.input.WindowsLineEndingInputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public static String URL = "http://training.skillo-bg.com:4200/users/login";

    WebDriverWait wait;


    @FindBy(css = "form .h4")
    WebElement signInHeader;

    @FindBy(xpath = "//div[@class='toast-message ng-star-inserted']")
    WebElement userNotFound;


    @FindBy(name = "usernameOrEmail")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void enterUsername(String username){
       // wait.until(ExpectedConditions.visibilityOf(usernameField));
        //usernameField.sendKeys(username);
        enterText(usernameField, username);
    }

    public void enterPassword(String password){
        enterText(passwordField, password);

    }
    public void clickSignIn(){
        clickElement(signInButton);
    }


    public String getSignInHeaderText(){ return getElementText(signInHeader);}
    public String getErrorText(){ return getElementText(userNotFound);}

    public void verifyUrl() {verifyUrl("http://training.skillo-bg.com:4200/users/login");}
    public void login(String username, String password){
     enterUsername(username);
     enterPassword(password);
     clickSignIn();
    }


}
