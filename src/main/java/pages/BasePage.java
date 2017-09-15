package pages;

import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;


public class BasePage {

    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void checkFillField(String value, WebElement element) {
        assertEquals(value, element.getAttribute("value"));
    }
}


