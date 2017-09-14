package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseSteps;

public class LoginPage extends BasePage {

    @FindBy(id = "index_email")
    public WebElement email;

    @FindBy(id = "index_pass")
    public WebElement password;

    @FindBy(id = "index_login_button")
    public WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }
}
