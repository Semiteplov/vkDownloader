import org.testng.annotations.Test;
import pages.AudioPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.*;


public class RefactoringTest extends BaseTest {

    @Test(description = "Test audio page", priority = 0)
    public void audioPageTest() throws FileNotFoundException {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillField(loginPage.getEmail(), properties.getProperty("login"));
        loginPage.fillField(loginPage.getPassword(), properties.getProperty("password"));

        loginPage.checkFillField(properties.getProperty("login"), loginPage.getEmail());
        loginPage.checkFillField(properties.getProperty("password"), loginPage.getPassword());

        loginPage.submit();

        MainPage mainPage = new MainPage(driver);
        AudioPage audioPage = new AudioPage(driver);
        mainPage.clickOnMyAudio();
        mainPage.checkMusicPage();
        audioPage.executeDownloadScript();

        String audioFileName = audioPage.downloadElems(audioPage.getAudioElems(), properties.getProperty("pathToDownload"));

        assert new File(properties.getProperty("pathToDownload").concat(audioFileName)).exists();
    }
}
