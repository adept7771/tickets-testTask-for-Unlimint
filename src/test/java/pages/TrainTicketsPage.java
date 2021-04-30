package pages;

import com.codeborne.selenide.SelenideElement;
import elements.OverAllMenu;

import static com.codeborne.selenide.Selenide.$x;

public class TrainTicketsPage {

    public OverAllMenu overAllMenu = new OverAllMenu();

    // Такие ужасные xpath из-за дичайшей обсфускации кода яндексом. Понимаю, что такие xpath работают на соплях
    // но в виду отсутсвия времени для нормальной разработки, сделал так.
    // Во многих местах я привязывался к тексту элемента, что в мультиязычном сервисе сделает сломает такие локаторы
    // но в данном случае других вариантов не вижу пока

    public SelenideElement
            departureLabel = $x("(//div[contains(@class, 'root_desktop')])[1]/label"),
            departureInput = $x("(//div[contains(@class, 'root_desktop')])[1]/label//input"),
            departureSpbItem = $x("//div[contains(text(), 'Санкт-Петербург')]"),

            arrivalInput = $x("(//div[contains(@class, 'root_desktop')])[2]/label//input"),
            arrivalMoscowItem = $x("(//div[contains(text(), 'Москва')])[2]"),

            whenInput = $x("(//div[contains(text(), 'Когда')])[2]"),
            whenInputTodayButton = $x("//div[@class='header']/div/button"),

            searchTicketsButton = $x("//button[@type='submit']//div[text()='Найти']");
}
