package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static constants.Timeout.TIMEOUT_10;
import static constants.Url.PAGE_URL;


public class Authorization {
    // локатор страницы авторизации
    private static final By PAGE_AUTHORIZATION = By.xpath(".//div[contains(@class, 'logo-auth mb-4')]");
    // локатор поля логин/емайл
    private static final By FIELD_EMAIL = By.id("sdo-login");
    // локатор поля пароль
    private static final By FIELD_PASSWORD = By.id("sdo-password");
    // локатор кнопки Войти
    private static final By BUTTON_LOGIN = By.cssSelector(".el-button.el-button--primary.custom-btn.custom-btn__active");
    // локатор ошибки Введенный пароль недействителен
    private static final By ERROR_INCORRECT_PASSWORD = By.xpath(".//p[text()='Введенный пароль недействителен.']");
    // локатор ошибки
    private static final By ERROR_BAD_CREDENTIALS = By.xpath(".//p[text()='Bad credentials.']");
    // локатор кнопки Забыли пароль
    private static final By BUTTON_FORGOT_PASSWORD = By.xpath(".//span[text()='Забыли пароль?']");
    // локатор поля восстановления емайл
    private static final By FIELD_EMAIL_RECOVERY = By.xpath("//label[contains(text(), 'Ваш логин или Email')]/preceding-sibling::input[1]");
    // локатор кнопки восстановить
    private static final By BUTTON_RESTORE = By.xpath(".//span[text()='Восстановить']");
    // сообщение что ссылка отправлена
    private static final By MESSAGE_LINK_SENT = By.xpath(".//p[text()='Ссылка на изменение пароля отправлена на почту']");


    private WebDriver driver;

    public Authorization(WebDriver driver) {
        this.driver = driver;
    }

    public Authorization open() {
        driver.get(PAGE_URL);
        return this;
    }

    public boolean waitPageAuthorization() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(PAGE_AUTHORIZATION));
        return driver.findElement(PAGE_AUTHORIZATION).isDisplayed();
    }

    public Authorization inputEmail(String email) {
        driver.findElement(FIELD_EMAIL).click();
        driver.findElement(FIELD_EMAIL).sendKeys(email);
        return this;
    }

    public Authorization inputPassword(String password) {
        driver.findElement(FIELD_PASSWORD).click();
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
        return this;
    }

    public Authorization clickButtonLogin() {
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }

    public Authorization login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickButtonLogin();
        return this;
    }

    public boolean displayedErrorIncorrectPassword() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_INCORRECT_PASSWORD));
        return driver.findElement(ERROR_INCORRECT_PASSWORD).isDisplayed();
    }

    public boolean displayedErrorBadCredentials() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_BAD_CREDENTIALS));
        return driver.findElement(ERROR_BAD_CREDENTIALS).isDisplayed();
    }

    public Authorization clickButtonForgotPassword() {
        driver.findElement(BUTTON_FORGOT_PASSWORD).click();
        return this;
    }

    public Authorization inputEmailRecovery(String emailRecovery) {
        driver.findElement(FIELD_EMAIL_RECOVERY).click();
        driver.findElement(FIELD_EMAIL_RECOVERY).sendKeys(emailRecovery);
        return this;
    }

    public Authorization clickButtonRestore() {
        driver.findElement(BUTTON_RESTORE).click();
        return this;
    }

    public Authorization loginRecovery(String email) {
        inputEmailRecovery(email);
        clickButtonRestore();
        return this;
    }

    public boolean displayedMessageLinkSent() {
        new WebDriverWait(driver, TIMEOUT_10)
                .until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_LINK_SENT));
        return driver.findElement(MESSAGE_LINK_SENT).isDisplayed();
    }
}
