package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProfilePage extends BasePage{

    private WebDriverWait wait;
    private final String BASE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = ".profile-user-settings > h2")
    WebElement usernameHeader;

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;


    public ProfilePage(WebDriver driver) {
       super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public String getUsernameHeaderText() {return getElementText(usernameHeader);}

        public void verifyUrl(){
         verifyUrlContains(BASE_URL);
        }
        public int getExistingPostsCount(){
        return existingPosts.size();
        }
        public void openPostByIndex(int index){
        clickElement(existingPosts.get(index));
        }
    }

