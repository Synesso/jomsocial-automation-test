package jomsocial.activity;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import jomsocial.pages.PageElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

/**
 * A page element holding a status activity.
 */
public class StatusActivity extends PageElement {

    public StatusActivity(WebElement element) {
        this(element, StatusActivity.class.getSimpleName());
    }

    public StatusActivity(WebElement element, String text) {
        super(element, "Status Activity with text \"" + text + "\"");
    }

    /**
     * Clicks the like button on this activity
     * @return self reference
     */
    public StatusActivity like() {
        element.findElement(By.linkText("Like")).click();
        waiter().until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.findElements(By.className("cStream-Likes")).size() > 0;
            }
        });
        return this;
    }

    /**
     * Clicks the unlike button on this activity
     * @return self reference
     */
    public StatusActivity unlike() {
        element.findElement(By.linkText("Unlike")).click();
        (new WebDriverWait(web, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.findElements(By.cssSelector(".cStream-Likes>a")).size() == 0;
            }
        });
        return this;
    }

    /**
     * Ensures that this activity is able to be liked. (i.e. a like button exists).
     * @return self reference
     */
    public StatusActivity mustBeLikeable() {
        if (element.findElements(By.linkText("Like")).isEmpty()) {
            throw new RuntimeException(String.format("Status Activity with text \"%s\" " +
                    "should have been likeable, but wasn't", text()));
        }
        return this;
    }

    /**
     * Ensures that this activity is able to be unliked. (i.e. an ulike button exists).
     * @return self reference
     */
    public StatusActivity mustBeUnlikeable() {
        if (element.findElements(By.linkText("Unlike")).isEmpty()) {
            throw new RuntimeException(String.format("Status Activity with text \"%s\" " +
                    "should have been unlikeable, but wasn't", text()));
        }
        return this;
    }

    /**
     * Returns the text of this status activity.
     * @return the text entered by the creator of the status activity.
     */
    public String text() {
        WebElement attachment = element.findElement(By.className("cStream-Attachment"));
        return attachment.getText();
    }

    /**
     * Posts a comment on this activity.
     * @return the comment page element.
     */
    public ActivityComment comment(final String message) {
        element.findElement(By.linkText("Comment")).click();
        WebElement textArea = element.findElement(By.name("comment"));
        textArea.sendKeys(message);
        element.findElement(By.tagName("button")).click();
        return waiter().until(new Function<WebDriver, ActivityComment>() {
            @Override
            public ActivityComment apply(WebDriver d) {
                return new ActivityComment(
                        // looks for the grandparent of the span that has the text of the comment
                        element.findElement(By.xpath(format(
                                "//div[contains(@class,'cStream-Comment')]/div/span[contains(text(), '%s')]/../..",
                                message))),
                        message
                );
            }
        });
    }

    /**
     * Deletes this activity
     */
    public void delete() {
        element.findElement(By.xpath("//a[contains(@title,'Delete')]")).click();
        modal().click("Yes");
        waiter().until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                try {
                    return !element.isDisplayed();
                } catch(StaleElementReferenceException good) {
                    return true;
                }
            }
        });
    }
}
