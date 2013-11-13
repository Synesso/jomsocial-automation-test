package jomsocial.activity;

import jomsocial.pages.PageElement;
import org.openqa.selenium.WebElement;

/**
 * A page element holding a comment on an activity.
 */
public class ActivityComment extends PageElement {

    public ActivityComment(WebElement element, String text) {
        super(element, String.format("ActivityComment with text \"%s\"", text));
    }
}
