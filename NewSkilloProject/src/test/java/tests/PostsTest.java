package tests;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import java.io.File;


public class PostsTest extends BaseTest {

@DataProvider(name = "getData")
    public Object[][] getData(){
    return new Object[][]{
            {"auto_user", "auto_pass", new File("src/test/java/upload/pretty.jpg"), "caption"}
    };
}
    @Test(dataProvider = "getData")
    public void testCreatePost(String username, String password, File file, String captionText) {
        System.out.println("1. Open homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2. Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page and get current post count");
        header.goToProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        int initialExistingPosts = profilePage.getExistingPostsCount();

        System.out.println("4. Go to new post");
        header.goToNewPost();
        NewPostPage newPostPage = new NewPostPage(driver);
        newPostPage.verifyUrl();

        System.out.println("5. Upload a new picture");
        newPostPage.uploadImage(file);

        System.out.println("6. Verify the image name is visible");
        newPostPage.waitForImageToShow();

        System.out.println("7. Verify the image name is correct");
        Assert.assertEquals(newPostPage.getImageFilename(), file.getName(), "Image did not show up");

        System.out.println("8. Populate the post caption");
        newPostPage.populateCaption(captionText);

        System.out.println("9. Click create post");
        newPostPage.submitPost();
        profilePage.verifyUrl();

        System.out.println("10. Verify the post number has increased");
        int existingPosts = profilePage.getExistingPostsCount();
        Assert.assertEquals(existingPosts, initialExistingPosts, "Incorrect posts");

        System.out.println("11. Open the latest post");
        profilePage.openPostByIndex(existingPosts - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogToAppear();


        System.out.println("12. Verify the post details");
        Assert.assertEquals(postModal.getPostUsername(),username,"Username is not correct!");
    }


}
