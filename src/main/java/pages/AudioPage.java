package pages;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPage extends BasePage {
    private static final String AUDIO_FILE_LOCATOR = "audio-download-link";
    private static final String AUDIO_FILES_LOCATOR = "//div[contains(@class, 'audio_row audio_row_with_cover')]";
    public AudioPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }

    public List<WebElement> getAudioElems() {
        while (driver.findElements(By.xpath(AUDIO_FILES_LOCATOR)).size() !=
                driver.findElements(By.className(AUDIO_FILE_LOCATOR)).size()) {
        }
        return driver.findElements(By.className(AUDIO_FILE_LOCATOR));
    }

    public List<String> downloadElems(List<WebElement> listElems, String downloadDir) {
        List<String> fileNames = new ArrayList<>();
        for(WebElement element: listElems) {
            element.click();
            makeScreenshot();
            String fileName = element.getAttribute("href").split("p[0-9]+/")[1].split(".mp3")[0].concat(".mp3");
            waitWhileFileDownloaded(downloadDir, fileName);
            fileNames.add(fileName);
        }

        return fileNames;
    }

    public void executeDownloadScript() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean needInjection = (Boolean)(js.executeScript("return this.$ === undefined;"));
        String script = "";

        if(needInjection) {
            URL u = Resources.getResource("jquery.js");
            try {
                script = Resources.toString(u, Charsets.UTF_8);
            } catch(IOException e) {
                e.printStackTrace();
            }
            js.executeScript(script);
        }

        script = "(function() {\n" +
                "   function decode_url(url){\n" +
                "      var tmp = {};\n" +
                "      AudioPlayerHTML5.prototype._setAudioNodeUrl(tmp, url);\n" +
                "      return tmp.src;\n" +
                "   }\n" +
                "\n" +
                "    function utf8_to_cp866(aa) {\n" +
                "        var bb = [], c = 0;\n" +
                "        for (var i = 0; i < aa.length; i++) {\n" +
                "            c = aa.charCodeAt(i);\n" +
                "            if (c > 127) {\n" +
                "                if (c >= 0x410 && c <= 0x43f)\n" +
                "                    bb.push(c + 0x80 - 0x410);\n" +
                "                if (c >= 0x440 && c <= 0x44f)\n" +
                "                    bb.push(c + 0xe0 - 0x440);\n" +
                "                if (c == 0x401)\n" +
                "                    bb.push(0xf0);\n" +
                "                if (c == 0x451)\n" +
                "                    bb.push(0xf1);\n" +
                "            } else {\n" +
                "                bb.push(c);\n" +
                "            }\n" +
                "        }\n" +
                "        return Uint8Array(bb);\n" +
                "    }\n" +
                "\n" +
                "    var ready = 0;\n" +
                "    var wget_list = \"\";\n" +
                "    var run = 0;\n" +
                "\n" +
                "    function sleep(milliseconds) {\n" +
                "       var start = new Date().getTime();\n" +
                "       for (var i = 0; i < 1e7; i++) {\n" +
                "           if ((new Date().getTime() - start) > milliseconds){\n" +
                "               break;\n" +
                "           }\n" +
                "     }\n" +
                "}" +
                "    function add_download_links() {\n" +
                "sleep(10000);\n" +
                "        run = 1;\n" +
                "        var audio_row_selector = '.audio_row';\n" +
                "\n" +
                "        if ($(audio_row_selector).length <= ready) {\n" +
                "            run = 0;\n" +
                "            return;\n" +
                "        }\n" +
                "\n" +
                "        var $this = $($(audio_row_selector)[ready]);\n" +
                "\n" +
                "        var audio_id_raw = $this.data('audio');\n" +
                "        var audio_id = audio_id_raw[1] + '_' + audio_id_raw[0];\n" +
                "\n" +
                "        var link = \"\";\n" +
                "\n" +
                "        $.ajax({\n" +
                "            url: 'https://vk.com/al_audio.php',\n" +
                "            method: 'post',\n" +
                "            async: false,\n" +
                "            data: {\n" +
                "                act: 'reload_audio',\n" +
                "                al: 1,\n" +
                "                ids: audio_id\n" +
                "            },\n" +
                "            success: function(response) {\n" +
                "                s_response = response.split('\"');\n" +
                "                //alert(\"s_response: \" + s_response);\n" +
                "                if (s_response.length > 1) {\n" +
                "                    link = decode_url(s_response[1]);\n" +
                "                    //alert(\"link: \" + link);\n" +
                "                    if (link != s_response[1]) {\n" +
                "                        var link_style = 'position: absolute; right: -12px; top: 12px; color: white; z-index: 100; background: red; border-radius: 3px 3px 3px 3px; padding: 2px 6px; font-size: 16px; opacity: 0.5;';\n" +
                "                        var song_name = $this.find('.audio_row__title_inner').text().trim();\n" +
                "                        var performer_name = $this.find('.audio_row__performer').text().trim();\n" +
                "                        var track_name = performer_name + ' - ' + song_name;\n" +
                "\n" +
                "                        $this.append('<a class=\"audio-download-link\" style=\"' + link_style +\n" +
                "                            '\" title=\"Скачать\" download=\"' +\n" +
                "                            track_name + '.mp3\" data-track_name=\"' +\n" +
                "                            track_name + '\" href=\"' + link +\n" +
                "                            '\" onclick=\"arguments[0].stopPropagation()\"> &#9835; </a>'\n" +
                "                        );\n" +
                "                        ready++;\n" +
                "                        wget_list += 'wget --no-check-certificate -c -O \"' + track_name + '.mp3\" ' + link + String.fromCharCode(0x0d, 0xa);\n" +
                "                    }\n" +
                "                } else {\n" +
                "                    if (response.match(/!bool/)) {\n" +
                "                        //alert(\"skip bad link\");\n" +
                "                        ready++;\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        setTimeout( function(){ add_download_links() }, 500);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    add_download_links();\n" +
                "    if (!ready)\n" +
                "        setTimeout( function(){ add_download_links() }, 2000);\n" +
                "\n" +
                "    window.addEventListener(\"scroll\", function(){\n" +
                "        if (!run)\n" +
                "            add_download_links();\n" +
                "    }, false);\n" +
                "\n" +
                "    $('body').keydown(function(e){\n" +
                "        if (e.which == 113) { // F2\n" +
                "            var blob;\n" +
                "            var p = window.navigator.platform;\n" +
                "\n" +
                "            if (p == \"Win32\" || p == \"Win64\") {\n" +
                "                blob = new Blob([utf8_to_cp866(wget_list)], {type: \"application/octet-binary\"});\n" +
                "                saveAs(blob, 'wget_list.bat', true);\n" +
                "            } else {\n" +
                "                blob = new Blob([wget_list], {type: \"text/plain;charset=utf-8\"});\n" +
                "                saveAs(blob, 'wget.sh', true);\n" +
                "            }\n" +
                "            sleep(10000);\n" +
                "        }\n" +
                "        setTimeout( function(){ add_download_links() }, 10000);\n" +
                "    });\n" +
                "})();";

        ((JavascriptExecutor)(driver)).executeScript(script);
    }

    private void waitWhileFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);

        while (!flag) {
            File[] dir_contents = dir.listFiles();
            for (File dir_content : dir_contents) {
                if (dir_content.getName().equals(fileName))
                    flag = true;
            }
        }
    }
}
