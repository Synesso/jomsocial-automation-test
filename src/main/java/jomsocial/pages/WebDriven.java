package jomsocial.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

public abstract class WebDriven {

//    static WebDriver web = new HtmlUnitDriver();
    protected static WebDriver web = new FirefoxDriver();

    /**
     * Returns whether one or more elements exists on the current page given a selector
     * @param by the selector
     * @return true if one or more elements exist, false otherwise.
     */
    public boolean elementExists(By by) {
        return web.findElements(by).size() > 0;
    }

}
