package jomsocial.pages;

import jomsocial.config.Config;
import jomsocial.config.DriverName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static jomsocial.config.DriverName.phantomjs;

public abstract class WebDriven {

    protected static WebDriver web;
    static {
        switch (DriverName.valueOf(Config.get("WEB_DRIVER", phantomjs))) {
            case phantomjs:
                web = phantomDriver();
                break;
            case firefox:
                web = firefoxDriver();
                break;
        }
        // oddly phantomjs will fail to see the logout element unless the screen is widened.
        web.manage().window().setSize(new Dimension(1024, 780));
        web.manage().timeouts().implicitlyWait(Config.get("TIMEOUT", 1500L), TimeUnit.MILLISECONDS);
    }

    private static WebDriver firefoxDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setJavascriptEnabled(true);
        return new FirefoxDriver();
    }

    private static WebDriver phantomDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                Config.get("PHANTOMJS_BIN"));
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, asList(
                "--web-security=false",
                "--ssl-protocol=any",
                "--ignore-ssl-errors=true"));
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                new String[] {"--logLevel=INFO"});
        return new PhantomJSDriver(capabilities);
    }

    /**
     * Returns whether one or more elements exists on the current page given a selector
     * @param by the selector
     * @return true if one or more elements exist, false otherwise.
     */
    public boolean elementExists(By by) {
        return web.findElements(by).size() > 0;
    }

    protected FluentWait<WebDriver> waiter() {
        return (new WebDriverWait(web, 10)).ignoring(NoSuchElementException.class);
    }

}
