package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AudioPage;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static steps.BaseSteps.properties;

public class ScenatioSteps {
    MainSteps mainSteps = new MainSteps();
    LoginSteps loginSteps = new LoginSteps();
    AudioSteps audioSteps = new AudioSteps();

    @When("заполняем поле email и password$")
    public void fillField() {
        loginSteps.fillField(new LoginPage().email, properties.getProperty("login"));
        loginSteps.fillField(new LoginPage().password, properties.getProperty("password"));
    }


    @Then("^Выполнено нажатие на логин$")
    public void submit() {
        loginSteps.submit();
    }

    @When("^Кликаем на Мои аудиозаписи$")
    public void clickOnMyAudio() {
        mainSteps.clickOnMyAudio();
        new BaseSteps().makeScreenshot();
    }

    @Then("^Проверяем страницу$")
    public void checkMusicPage() {
        mainSteps.checkMusicPage();
    }

    @Then("^Выполняем скрипт$")
    public void executeDownloadScript() {
        audioSteps.executeDownloadScript();
    }

    @Then("^Проверяем скачалась ли песня$")
    public void isDownloaded() {
        audioSteps.isDownloaded();
    }
}
