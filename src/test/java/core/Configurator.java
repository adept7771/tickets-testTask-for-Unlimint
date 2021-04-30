package core;

import com.codeborne.selenide.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static core.AttachmentsHelper.*;

public class Configurator {

    @BeforeEach
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Логика сетаппера такова, в приоритете настройки получаемые из SystemVariables, если они отсутсвуют, то
        // грузятся из properties класса.

        String runViaSelenoid = System.getProperty("runViaSelenoid", Properties.runViaSelenoid);
        Boolean selenoidEnableVideo = Boolean.valueOf(System.getProperty("selenoidEnableVideo", Properties.selenoidEnableVideo));
        Boolean selenoidEnableVNC = Boolean.valueOf(System.getProperty("selenoidEnableVNC", Properties.selenoidEnableVNC));
        String selenoidInstance = Properties.selenoidInstance.val;

        boolean startMaximized = Boolean.parseBoolean(System.getProperty("startMaximized", Properties.startMaximized));
        long timeout = Long.parseLong(System.getProperty("selenideWaitTimeout", Properties.selenideWaitTimeout));

        if (runViaSelenoid.equals("true")) {
            capabilities.setCapability("enableVideo", selenoidEnableVideo);
            capabilities.setCapability("videoFrameRate", 24);
            capabilities.setCapability("enableVNC", selenoidEnableVNC);

            Configuration.remote = System.getProperty("selenoidInstance", selenoidInstance);
        }

        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("browser", Properties.browser);
        Configuration.startMaximized = startMaximized;
        Configuration.timeout = timeout;

        addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    public void tearDown() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
}
