package jomsocial.activity;

import jomsocial.pages.WebDriven;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusActivity extends WebDriven {

    // todo - Jem WIP - Capture the li id so that it can be easily obtained again.

    private final WebElement element;

    public StatusActivity(WebElement element) {
        this.element = element;
    }

    public StatusActivity like() {
        element.findElement(By.linkText("Like")).click();
        waiter().until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.findElements(By.className("cStream-Likes")).size() > 0;
            }
        });
        return this;
    }

    public StatusActivity unlike() {
        element.findElement(By.linkText("Unlike")).click();
        (new WebDriverWait(web, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.findElements(By.cssSelector(".cStream-Likes>a")).size() == 0;
            }
        });
        return this;
    }

    public StatusActivity mustBeLikeable() {
        if (element.findElements(By.linkText("Like")).isEmpty()) {
            throw new RuntimeException(String.format("Status Activity with text \"%s\" " +
                    "should have been likeable, but wasn't", text()));
        }
        return this;
    }

    public StatusActivity mustBeUnlikeable() {
        if (element.findElements(By.linkText("Unlike")).isEmpty()) {
            throw new RuntimeException(String.format("Status Activity with text \"%s\" " +
                    "should have been unlikeable, but wasn't", text()));
        }
        return this;
    }

    public String text() {
        WebElement attachment = element.findElement(By.className("cStream-Attachment"));
        return attachment.getText();
    }

    public StatusActivity mustBeVisible() {
        if (!element.isDisplayed()) {
            throw new RuntimeException(String.format("Status Activity with text \"%s\" " +
                    "should have been visible, but wasn't", text()));
        }
        return this;
    }
}
