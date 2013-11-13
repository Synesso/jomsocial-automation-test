package jomsocial.pages;

import com.google.common.base.Function;
import jomsocial.config.Config;
import jomsocial.activity.StatusActivity;
import jomsocial.resources.Photo;
import jomsocial.users.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * Represents the page at the base URL, known as the front page.
 */
public class FrontPage extends WebDriven {

    /**
     * Logs in as the given user.
     * @param user to log in as
     * @return self reference
     */
    public FrontPage loginAs(User user) {
        if (!elementExists(By.id("form-login"))) {
            logout();
        }
        web.findElement(By.id("username")).sendKeys(user.name);
        web.findElement(By.id("password")).sendKeys(user.password);
        web.findElement(By.id("submit")).click();
        open(); // to get off the profile page
        return this;
    }

    /**
     * Logs out
     * @return self reference
     */
    public FrontPage logout() {
        logoutButton().click();
        return this;
    }

    /**
     * Posts a random status message
     * @return the status message posted
     */
    public StatusActivity shareStatus() {
        final String statusMessage = UUID.randomUUID().toString();
        return shareStatus(statusMessage);
    }

    /**
     * Posts a status message with specified text
     * @param status the text of the status message
     * @return the status message posted
     */
    public StatusActivity shareStatus(final String status) {
        web.findElement(By.cssSelector(".type-message>a")).click();
        web.findElement(By.className("creator-message")).sendKeys(status);
        web.findElement(By.className("creator-share")).click();
        return waiter().until(new Function<WebDriver, StatusActivity>() {
            public StatusActivity apply(WebDriver d) {
                return new StatusActivity(
                        // looks for the grandparent of the div that has the text of the status message
                        web.findElement(By.xpath(format(
                                "//li[contains(@class,'feed-profile')]/div/div[contains(text(), '%s')]/../..",
                                status))),
                        status
                );
            }
        });
    }

    /**
     * Finds all the status activities that have the text provided.
     * @param textsArr 0..n Strings of text to identify the status activities
     * @return the found status activities [of size 0..textsArr.length]
     */
    public List<StatusActivity> statusActivitiesWithText(String... textsArr) {
        List<String> texts = asList(textsArr);
        List<StatusActivity> xs = new ArrayList<>();
        for (StatusActivity activity : statusActivities()) {
            if (texts.contains(activity.text())) {
                xs.add(activity);
            }
            if (xs.size() == texts.size()) {
                // usually the ones we want are at the top of a potentially long list, break early when we have them.
                break;
            }
        }
        return xs;
    }

    /**
     * @return List of all status activities on the page.
     */
    public List<StatusActivity> statusActivities() {
        List<StatusActivity> list = new ArrayList<>();
        List<WebElement> feedProfileElements = web.findElements(By.className("feed-profile"));
        for (WebElement element : feedProfileElements) {
            list.add(new StatusActivity(element));
        }
        return list;
    }

    /**
     * Ask the browser to open this page
     * @return self reference
     */
    public FrontPage open() {
        web.get(Config.get("BASE_URL"));
        return this;
    }
}
