package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import elements.OverAllMenu;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class TrainTicketsResultsPage {

    public SelenideElement
            hideZeroPriceCheckbox = $x("//input[@class='Checkbox-Control']"),
            sortByPriceCheckbox = $x("//span[contains(text(), 'цене')]");

    public ElementsCollection abstractTicketPrice = $$x("//div[contains(@class, 'root_desktop')]" +
            "//div[contains(@style, 'flex-direction: column')]//span[contains(text(), '₽')]");

    // выбор типа сортировки
    public enum SortBy {
        PRICE, ARRIVAL_TIME, DEPARTURE_TIME, TOTAL_TIME
    }
}
