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

public class FrontPage extends WebDriven {

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

    public FrontPage logout() {
        logoutButton().click();
        return this;
    }

    public StatusActivity shareStatus() {
        final String statusMessage = UUID.randomUUID().toString();
        shareStatus(statusMessage);
        return waiter().until(new Function<WebDriver, StatusActivity>() {
            public StatusActivity apply(WebDriver d) {
                return new StatusActivity(
                    // looks for the grandparent of the div that has the text of the status message
                    web.findElement(By.xpath(format(
                            "//li[contains(@class,'feed-profile')]/div/div[contains(text(), '%s')]/../..",
                            statusMessage)))
                );
            }
        });
    }

    public FrontPage shareStatus(String status) {
        web.findElement(By.cssSelector(".type-message>a")).click();
        web.findElement(By.className("creator-message")).sendKeys(status);
        web.findElement(By.className("creator-share")).click();
        return this;
    }

    public FrontPage sharePhoto(Photo photo) {
        // todo
        return this;
    }

    public List<StatusActivity> statusActivitiesWithText(String... textsArr) {
        List<String> texts = asList(textsArr);
        List<StatusActivity> xs = new ArrayList<>();
        for (StatusActivity activity : statusActivities()) {
            if (texts.contains(activity.text())) {
                xs.add(activity);
            }
        }
        return xs;
    }

    public StatusActivity statusActivityWithText(String text) {
        List<StatusActivity> activities = statusActivitiesWithText(text);
        return (activities.isEmpty()) ? null : activities.get(0);
    }

    public List<StatusActivity> statusActivities() {
        List<StatusActivity> list = new ArrayList<>();
        List<WebElement> feedProfileElements = web.findElements(By.className("feed-profile"));
        for (WebElement element : feedProfileElements) {
            list.add(new StatusActivity(element));
        }
        return list;
    }

    public FrontPage open() {
        web.get(Config.get("BASE_URL"));
        return this;
    }

    private WebElement logoutButton() {
        // there are two logout anchor tags. Only the second one is visible/clickable.
        return web.findElements(By.cssSelector("a[title='Logout']")).get(1);
    }
}
