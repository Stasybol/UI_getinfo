package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Locale;
import java.util.Optional;


public class WebDriverFactory {
    private static final String DEFAULT_BROWSER = "CHROME";

    public static WebDriver createDriver() {
        String browserName = Optional.ofNullable(System.getenv("BROWSER_NAME"))
                .orElse(DEFAULT_BROWSER)
                .toUpperCase(Locale.ROOT);

        return createForName(browserName);
    }

    private static WebDriver createForName(String browserName) {
        return switch (browserName) {
            case "CHROME" -> createChromeDriver();
            case "FIREFOX" -> createFirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}

