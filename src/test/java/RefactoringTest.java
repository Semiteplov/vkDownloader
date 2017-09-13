import org.testng.annotations.Test;
import pages.AudioPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.*;
import java.util.List;


public class RefactoringTest extends BaseTest {
    private static final String LOGIN = "";
    private static final String PASSWORD = "";
    @Test(description = "Login to account", priority = 0)
    public void loginTest() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillField(loginPage.getEmail(), LOGIN);
        loginPage.fillField(loginPage.getPassword(), PASSWORD);

        loginPage.checkFillField(LOGIN, loginPage.getEmail());
        loginPage.checkFillField(PASSWORD, loginPage.getPassword());

        loginPage.submit();
    }

    @Test(description = "Test audio page", priority = 1)
    public void audioPageTest() throws FileNotFoundException {
        driver.get(baseUrl + "audio");
        MainPage mainPage = new MainPage(driver);
        AudioPage audioPage = new AudioPage(driver);
        mainPage.clickOnMyAudio();
        mainPage.checkMusicPage();
        audioPage.executeDownloadScript();

        List<String> audioFileNames = audioPage.downloadElems(audioPage.getAudioElems(), properties.getProperty("pathToDownload"));

        for (String audioName: audioFileNames) {
            assert new File(properties.getProperty("pathToDownload").concat(audioName)).exists();
        }
    }
}
