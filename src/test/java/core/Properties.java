package core;

import com.codeborne.selenide.Browsers;

public class Properties {
    // мне не очень нравится использование настоящих .properties файлов, так как их обработка в коде всегда вылазит
    // в длиннющие конструкции. Можно вообще подключить библиотеку owner, которая будет пропертя грузить. Но там появится
    // дополнительная логика, которая не всегда уместна.
    // Все данные проперти могут быть скормлены мавену через -D. Например -DstartMaximized="false"
    // можно комбинировать параметры запуска в строке например так:
    // mvn -Dbrowser=Opera -DstartMaximized=false -Dtest=TrainTicketsTests test
    // для генерации аллюр отчета mvn allure:install единожды, потом mvn allure:serve

    public static String runViaSelenoid = "false";
    public static SelenoidInstance selenoidInstance = SelenoidInstance.remoteSelenoid; // address of selenoid machine
    public static WebVideoStorage webVideoStorage = WebVideoStorage.remoteStorage; // video storage for selenoid

    public static String selenoidEnableVideo = "true";
    public static String selenoidEnableVNC = "true";

    public static String startMaximized = "true";
    public static String selenideWaitTimeout = "6000";
    public static String browser = Browsers.CHROME;

    /* Вспомогательные классы */

    enum SelenoidInstance {
        localSelenoid("http://localhost:4444/wd/hub/"),
        remoteSelenoid("https://REMOTE_ADDR:4444/wd/hub/");

        public String val;
        SelenoidInstance(String val) {
            this.val = val;
        }
    }

    enum WebVideoStorage {
        localStorage("http://localhost:4444/video/"),
        remoteStorage("https://REMOTE_ADDR/video/");

        public String val;
        WebVideoStorage(String val) {
            this.val = val;
        }
    }
}
