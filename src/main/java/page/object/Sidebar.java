package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static constants.Timeout.*;


public class Sidebar {
    // локатор лоадера
    private static final By LOADER = By.xpath("//div[contains(@class, 'el-loading-mask') and (contains(@class, 'is-fullscreen') or contains(@class, 'fade-leave-active'))]");
    // локатор текста Мое обучение
    private static final By TEXT_MY_EDUCTION = By.xpath(".//span[text()='Мое обучение']");
    // локатор вкладки учебный центр
    private static final By TAB_TRAINING_CENTER = By.xpath(".//span[text()='Учебный центр']");
    // локатор раздела компания
    private static final By SECTION_COMPANY = By.xpath(".//a[@class='w-full' and text()='Компания']");

    private WebDriver driver;

    public Sidebar(WebDriver driver) {
        this.driver = driver;
    }

    public Sidebar waitForAllLoadersToDisappear() {
        try {
            List<WebElement> loaders = new WebDriverWait(driver, TIMEOUT_3)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOADER));

            loaders.forEach(loader ->
                    new WebDriverWait(driver, TIMEOUT_15)
                            .until(ExpectedConditions.invisibilityOf(loader))
            );

        } catch (TimeoutException e) {
        }
        return this;
    }

    public boolean openSidebar(){
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_MY_EDUCTION));
        return driver.findElement(TEXT_MY_EDUCTION).isDisplayed();
    }

    public Sidebar clickTabTrainingCenter() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_10);
        wait.until(ExpectedConditions.elementToBeClickable(
               TAB_TRAINING_CENTER)).click();
        return this;
    }

    public boolean displayedSectionCompany() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(SECTION_COMPANY));
        return driver.findElement(SECTION_COMPANY).isDisplayed();
    }

    public Sidebar clickSectionCompany() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(SECTION_COMPANY));
        driver.findElement(SECTION_COMPANY).click();
        return this;
    }
}
