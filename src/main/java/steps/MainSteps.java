package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertEquals;

public class MainSteps {

    @Step("переход в раздел Моя музыка")
    public void clickOnMyAudio() {
        new MainPage().myAudioButton.click();
        new BaseSteps().makeScreenshot();
    }

    @Step("проверка текущей страницы")
    public void checkMusicPage() {
        new BaseSteps().getWait().until(ExpectedConditions.visibilityOf(
                BaseSteps.getDriver().findElement(By.xpath("//h2[contains(text(),'Аудиозаписи')]"))));
        new BaseSteps().makeScreenshot();
        assertEquals("Моя музыка", BaseSteps.getDriver().getTitle());
    }
}
