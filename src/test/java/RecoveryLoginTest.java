import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.Authorization;
import static constants.Credentials.LOGIN;


public class RecoveryLoginTest {
    private WebDriver driver;
    private Authorization authorization;

    @Before
    public void before() {
        driver = WebDriverFactory.createDriver();
        authorization = new Authorization(driver);
    }

    @Test
    public void loginRecoveryTest(){
        authorization.open();
        Assert.assertTrue(authorization.waitPageAuthorization());
        authorization.clickButtonForgotPassword().loginRecovery(LOGIN);
        Assert.assertTrue(authorization.displayedMessageLinkSent());
    }

    @After
    public void after() {
        driver.quit();
    }
}

