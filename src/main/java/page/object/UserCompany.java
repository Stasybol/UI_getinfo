package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static constants.Timeout.TIMEOUT_10;


public class UserCompany {
    // текст заголовка компания
    private static final String TEXT_TITLE_COMPANY = "Компания";
    // локатор руководителя
    private static final By LINK_DIRECTOR = By.xpath("//div[@class='description-value']//a[contains(@href, 'user')]");

    private WebDriver driver;

    public UserCompany(WebDriver driver) {
        this.driver = driver;
    }

    public UserCompany openingUserCompany() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_10);
        try {
            wait.until(ExpectedConditions.titleContains(TEXT_TITLE_COMPANY));
        } catch (Exception e) {
            throw new WebDriverException(
                    "Не найден заголовок: " + TEXT_TITLE_COMPANY);
        }
        return this;
    }

    public UserCompany clickLinkDirector() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(LINK_DIRECTOR));
        driver.findElement(LINK_DIRECTOR).click();
        return this;
    }
}
