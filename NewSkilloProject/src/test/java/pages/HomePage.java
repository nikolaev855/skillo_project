package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage{
    private WebDriverWait wait;
    private final String URL = "http://training.skillo-bg.com:4200/posts/all";

    public HomePage(WebDriver driver) {
    super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    public void navigate(){
        driver.get(URL);}


    public void verifyUrl(){
       verifyUrl(URL);

    }
}
