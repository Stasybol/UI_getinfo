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


public class SectionCompanyTest {
    private WebDriver driver;
    private Authorization authorization;
    private Sidebar sidebar;

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
    }

    @Test
    public void sectionCompanyTest(){
        sidebar.clickTabTrainingCenter();
        Assert.assertTrue(sidebar.displayedSectionCompany());
    }

    @After
    public void after() {
        driver.quit();
    }
}


