package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import static org.junit.Assert.assertEquals;


public class MainPage extends BasePage {
    public WebDriverWait wait;

    @FindBy(id = "l_aud")
    public WebElement myAudioButton;

    public MainPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
        wait = new WebDriverWait(BaseSteps.getDriver(),5);
    }

    public void clickOnMyAudio() {
        myAudioButton.click();
        new BaseSteps().makeScreenshot();
    }

    public void checkMusicPage() {
        wait.until(ExpectedConditions.visibilityOf(
                BaseSteps.getDriver().findElement(By.xpath("//h2[contains(text(),'Аудиозаписи')]"))));
        new BaseSteps().makeScreenshot();
        assertEquals("Моя музыка", BaseSteps.getDriver().getTitle());
    }
}
