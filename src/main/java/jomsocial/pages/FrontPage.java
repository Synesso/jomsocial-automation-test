package jomsocial.pages;

import jomsocial.Config;
import jomsocial.activity.StatusActivity;
import jomsocial.resources.Photo;
import jomsocial.users.User;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ScreenshotException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<StatusActivity> statusActivities() {
        List<StatusActivity> list = new ArrayList<>();
        for (WebElement element : web.findElements(By.className("feed-profile"))) {
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
