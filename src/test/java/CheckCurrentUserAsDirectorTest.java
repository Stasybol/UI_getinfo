import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.Authorization;
import page.object.Sidebar;
import page.object.User;
import page.object.UserCompany;

import static constants.Credentials.LOGIN;
import static constants.Credentials.PASSWORD;

public class CheckCurrentUserAsDirectorTest {
    private WebDriver driver;
    private Authorization authorization;
    private Sidebar sidebar;
    private UserCompany userCompany;
    private User user;

    @Before
    public void before(){
        driver = WebDriverFactory.createDriver();
        authorization = new Authorization(driver);
        authorization.open();
        Assert.assertTrue(authorization.waitPageAuthorization());
        authorization.login(LOGIN, PASSWORD);
        sidebar = new Sidebar(driver);
        sidebar.waitForAllLoadersToDisappear();
        Assert.assertTrue(sidebar.openSidebar());
        sidebar.clickTabTrainingCenter();
        Assert.assertTrue(sidebar.displayedSectionCompany());
        sidebar.clickSectionCompany();
    }

    @Test
    public void userAsDirectorTest(){
        userCompany = new UserCompany(driver);
        userCompany.openingUserCompany();
        userCompany.clickLinkDirector();
        user = new User(driver);
        user.openingNewTab();
        Assert.assertEquals("Руководить компании и текущий пользователь не совпадают", LOGIN, user.textLogin());
    }

    @After
    public void after() {
        driver.quit();
    }
}

