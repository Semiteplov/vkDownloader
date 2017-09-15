package steps;

import org.openqa.selenium.WebElement;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Step;

import static org.junit.Assert.assertEquals;


public class LoginSteps {

    @Step("выполнено нажатие на Log in")
    public void submit() {
        new LoginPage().loginButton.click();
    }

    @Step("заполнение поля {0} значением {1}")
    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    @Step("проверяем правильность заполнения поля {1} значением {0}")
    public void checkFillField(String value, WebElement element) {
        assertEquals(value, element.getAttribute("value"));
    }

}
