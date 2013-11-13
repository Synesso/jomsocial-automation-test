package jomsocial.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * Page elements are objects that model portions of the displayed page that have a specific meaning. For example,
 * the StatusActivity section, or a StatusActivity's ActivityComment. They are not pages in themselves, but always only
 * part of page.
 */
public abstract class PageElement extends WebDriven {
    protected final WebElement element;
    protected final String description;

    protected PageElement(WebElement element, String description) {
        this.element = element;
        this.description = description;
    }

    /**
     * Ensures that this element is visible
     * @return self reference
     * @throws RuntimeException when the element is not visible
     */
    public PageElement mustBeVisible() {
        try {
            if (!element.isDisplayed()) {
                throw new RuntimeException(String.format("%s should have been visible, but wasn't", description));
            }
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException(String.format("%s should have been visible, but wasn't", description), e);
        }
        return this;
    }

    /**
     * Ensures that this element is not visible
     * @return self reference
     * @throws RuntimeException when the element is visible
     */
    public PageElement mustNotBeVisible() {
        try {
            if (element.isDisplayed()) {
                throw new RuntimeException(String.format("%s should have been invisible, but wasn't", description));
            }
        } catch (StaleElementReferenceException good) {
            // This is a good thing. The element is not even in the DOM anymore
        }
        return this;
    }

}
