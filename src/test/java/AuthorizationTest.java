import constants.RandomData;
import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.Authorization;
import page.object.Sidebar;


import static constants.Credentials.LOGIN;
import static constants.Credentials.PASSWORD;

public class AuthorizationTest {
    private WebDriver driver;
    private Authorization authorization;
    private Sidebar sidebar;

    @Before
    public void before(){
        driver = WebDriverFactory.createDriver();
        authorization = new Authorization(driver);
        authorization.open();
        Assert.assertTrue(authorization.waitPageAuthorization());
    }

    @Test
    public void successLoginTest(){
        authorization.login(LOGIN, PASSWORD);
        sidebar = new Sidebar(driver);
        Assert.assertTrue(sidebar.openSidebar());
    }

    @Test
    public void errorIncorrectPasswordTest(){
        authorization.login(LOGIN, RandomData.password());
        Assert.assertTrue(authorization.displayedErrorIncorrectPassword());
    }

    @Test
    public void errorBadCredentialsTest(){
        authorization.login(RandomData.email(), PASSWORD);
        Assert.assertTrue(authorization.displayedErrorBadCredentials());
    }

    @After
    public void after() {
        driver.quit();
    }
}
