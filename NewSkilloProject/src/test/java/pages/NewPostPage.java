package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class NewPostPage extends BasePage {
    private final String URL = "http://training.skillo-bg.com:4200/posts/create";
    @FindBy(css = ".image-preview")
    WebElement imagePreview;
    @FindBy(css = "input.input-lg")
    WebElement fileNameField;
    @FindBy(name = "caption")
    WebElement captionInput;

    @FindBy(id = "create-post")
    WebElement submitBtn;

    @FindBy(css = "input.file[type='file']")
    WebElement fileUploadInput;
    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/app-post-modal/div/div[2]/div[3]/div/div/div/div[1]/i[1]")
    WebElement likeButton;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-all-posts/div/div/div[1]/app-post-detail/div/div[1]")
    WebElement postPhoto;

    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifyUrl(){
        verifyUrl(URL);
    }
    public void uploadImage(File file){
        fileUploadInput.sendKeys(file.getAbsolutePath());
    }
    public void waitForImageToShow(){
        smallWait.until(ExpectedConditions.visibilityOf(imagePreview));
    }
    public String getImageFilename(){
        smallWait.until(ExpectedConditions.visibilityOf(fileNameField));
        return fileNameField.getAttribute("placeholder");
    }
    public void populateCaption(String captionText){
        enterText(captionInput, captionText);
    }
    public void submitPost(){
        clickElement(submitBtn);
    }
    public void clickLike(){
        clickElement(likeButton);
    }
    public void clickPhoto(){
        clickElement(postPhoto);
    }
}
