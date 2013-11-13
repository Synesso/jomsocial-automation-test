package jomsocial.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The modal dialog box.
 */
public class ModalBox extends PageElement {
    public ModalBox(WebElement element) {
        super(element, "The modal dialog box");
    }

    /**
     * In the modal, click the button that has the given text
     * @param buttonText clicks the button with this text
     */
    public void click(String buttonText) {
        element.findElement(By.xpath(String.format("//button[contains(text(), '%s')]", buttonText))).click();
    }
}
