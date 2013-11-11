package jomsocial.pages;

import jomsocial.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.util.Arrays.asList;

public abstract class WebDriven {

    protected static WebDriver web;
    static {
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
        web = new PhantomJSDriver(capabilities);
        // oddly phantomjs will fail to see the logout element unless the screen is widened.
        web.manage().window().setSize(new Dimension(1024, 780));
    }

    /**
     * Returns whether one or more elements exists on the current page given a selector
     * @param by the selector
     * @return true if one or more elements exist, false otherwise.
     */
    public boolean elementExists(By by) {
        return web.findElements(by).size() > 0;
    }

}
