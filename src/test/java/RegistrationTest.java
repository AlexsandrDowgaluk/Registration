import com.java4s.test.API.SendPost;
import com.java4s.test.config.BrowserConfig;
import com.java4s.test.pages.RegisterPage;
import com.java4s.test.utils.Utils;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class RegistrationTest extends BrowserConfig {

    private EventFiringWebDriver driver;
    private RegisterPage registerPage;
    private SendPost sendPost;
    private Utils utils;


    @BeforeClass
    public void setUp(){
        driver = getConfiguredDriver();

        registerPage = new RegisterPage(driver);
        sendPost = new SendPost();
        utils = new Utils(driver);
    }

    @AfterClass
    public  void tearDown(){
        BrowserConfig.quitDriver(driver);
    }

    @DataProvider(name = "testData")
    public Object[][] credentials(){

        return new Object[][]{
                {"User","1","123456"},
                {"", "1", "123456"},
                {"Usertest","1","123456"}
        };
    }

    @Test(dataProvider = "testData")
    public void regTest (String name, String id, String contactNumber) throws IOException {

        registerPage.openHomePage();
        registerPage.findNameTextField(name);
        registerPage.findIdTextField(id);
        registerPage.findContactNumberTextField(contactNumber);
        registerPage.findRegisterButton();

        Assert.assertTrue(registerPage.findConfirmMessage());

        sendPost.sendPost(name);

        Assert.assertEquals(sendPost.getName(), name);
        Assert.assertEquals(sendPost.getId(), id);
        Assert.assertEquals(sendPost.getContactNumber(), contactNumber);

    }

    @AfterMethod
    public void afterTest(ITestResult result) throws IOException {

        if(result.getStatus() == ITestResult.FAILURE){
            utils.takeScreenshot();
            utils.showConsoleLog();
        }

        if(result.getStatus() == ITestResult.SUCCESS){
            //do something
        }

        driver.manage().deleteAllCookies();
    }


}
