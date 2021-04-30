package tests;

import com.codeborne.selenide.Selenide;
import core.Configurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.AviaTicketsPage;
import steps.TrainSteps;

import java.util.ArrayList;

import static pages.TrainTicketsResultsPage.SortBy.PRICE;

@Execution(ExecutionMode.CONCURRENT)
public class TrainTicketsTests extends Configurator {

    final String owner = "Dmitry Potapov";
    final String url = "https://travel.yandex.ru/";
    final String projectName = "YandexTickets";

    /**
     * Просто найти самый дешевый билет, это не тест. Тест должен что то ПРОВЕРЯТЬ. Я решил проверить сортировку.
     * Первый элемент в коллекции цен билетов и должен быть самым дешевым + поверка всей коллекции по возрастанию цены.
     */
    @DisplayName("Проверка сортировки цен на билеты")
    @Feature("Сортировка цен")
    @Story("Билеты на поезд")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void sortingTest() {

        // адрес можно вынести в конфигуратор, так как он может отличаться на разных стендах
        Selenide.open(url);

        // здесь пример page element. Не вижу смысла такой маленький шаг выносить в Steps. Но если логика более сложная
        // то можно и вынести
        aviaTicketsPage.overAllMenu.trainTicketsButton.click();

        trainSteps.chooseDepartureSPB();
        trainSteps.chooseArrivalMoscow();
        trainSteps.chooseDateToday();

        trainSteps.initiateSearch();
        trainSteps.hideZeroPrice();

        trainSteps.sortResultsBy(PRICE);

        ArrayList<Integer> ticketPrices = trainSteps.getResultsPrices();
        trainSteps.checkPriceSorting(ticketPrices);
    }

    // Степы можно вынести в интерфейсы или наследовать откуда-то. Единственно сразу надо будет проверить, не будет
    // ли каких то не явных проблем с многопоточностью.
    AviaTicketsPage aviaTicketsPage = new AviaTicketsPage();
    TrainSteps trainSteps = new TrainSteps();
}

