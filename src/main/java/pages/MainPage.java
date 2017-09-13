package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class MainPage extends BasePage {

    @FindBy(id = "l_aud")
    WebElement myAudioButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }

    public void clickOnMyAudio() {
        myAudioButton.click();
        makeScreenshot();
    }

    public void checkMusicPage() {
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//h2[contains(text(),'Аудиозаписи')]"))));
        makeScreenshot();
        assertEquals("Моя музыка", driver.getTitle());
    }
}
