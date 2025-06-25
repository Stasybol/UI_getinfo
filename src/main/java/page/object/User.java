package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static constants.Timeout.TIMEOUT_10;


public class User {
    // заголовок вкладки пользователя
    private static final String TEXT_TITLE_USER = "Пользователь";
    // локатор логина руководителя
    private static final By LOGIN_DIRECTOR = By.xpath("//div[@class='description normal-size']/div[@class='description-value']");

    public User(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public User openingNewTab() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_10);
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);

            try {
                wait.until(ExpectedConditions.titleContains(TEXT_TITLE_USER));
                return this;
            } catch (Exception ignored) {
            }
        }

        driver.switchTo().window(originalWindow);
        throw new WebDriverException("Не найдена вкладка с заголовком: " + TEXT_TITLE_USER);
    }

    public String textLogin(){
        new WebDriverWait(driver, TIMEOUT_10)
               .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_DIRECTOR));
        return driver.findElement(LOGIN_DIRECTOR).getText();
    }
}
