package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;

import static org.testng.Assert.assertEquals;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @Attachment(type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void checkFillField(String value, WebElement element) {
        assertEquals(value, element.getAttribute("value"));
    }
}


