import org.junit.Test;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Title;
import steps.AudioSteps;
import steps.BaseSteps;
import steps.LoginSteps;
import steps.MainSteps;

import java.io.*;


public class RefactoringTest extends BaseSteps {
    MainSteps mainSteps = new MainSteps();
    LoginSteps loginSteps = new LoginSteps();
    AudioSteps audioSteps = new AudioSteps();

    @Title("Загрузка музыки")
    @Test
    public void audioPageTest() throws FileNotFoundException {
        getDriver().get(baseUrl);
        loginSteps.fillField(new LoginPage().email, properties.getProperty("login"));
        loginSteps.fillField(new LoginPage().password, properties.getProperty("password"));

        loginSteps.checkFillField(properties.getProperty("login"), new LoginPage().email);
        loginSteps.checkFillField(properties.getProperty("password"), new LoginPage().password);

        loginSteps.submit();

        mainSteps.clickOnMyAudio();
        mainSteps.checkMusicPage();

        audioSteps.executeDownloadScript();

        audioSteps.isDownloaded();
    }
}
