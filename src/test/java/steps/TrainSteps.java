package steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import pages.AviaTicketsPage;
import pages.TrainTicketsPage;
import pages.TrainTicketsResultsPage;

import java.util.ArrayList;

public class TrainSteps {

    // эти методы можно кастомизировать. В данный момент жестко захардкожено питер и москва (нет времени на реализацию).
    // Можно на вход отправлять название города, если надо. И дальше пусть метод уже выбирает нужный нам город.
    @Step("Выбираем отправление из СПБ")
    public void chooseDepartureSPB() {
        trainTicketsPage.departureLabel.click();
        trainTicketsPage.departureLabel.sendKeys(Keys.BACK_SPACE);
        trainTicketsPage.departureInput.val("Санкт-Петербург");
        trainTicketsPage.departureSpbItem.click();
    }

    @Step("Выбираем прибытие в москву")
    public void chooseArrivalMoscow() {
        trainTicketsPage.arrivalInput.val("Москва");
        trainTicketsPage.arrivalMoscowItem.click();
    }

    // дату тоже можно кастомизировать
    @Step("Выбираем дату на сегодня")
    public void chooseDateToday() {
        trainTicketsPage.whenInput.click();
        trainTicketsPage.whenInputTodayButton.click();
    }

    @Step("Запускаем поиск")
    public void initiateSearch() {
        trainTicketsPage.searchTicketsButton.click();
    }

    @Step("Прячем нулевые цены, если они есть")
    public void hideZeroPrice() {
        if (trainTicketsResultsPage.hideZeroPriceCheckbox.isDisplayed()) {
            trainTicketsResultsPage.hideZeroPriceCheckbox.click();
        }
    }

    // пример кастомизации выбора сортировки в одном методе. Можно через перегрузку сделать
    @Step("Сортируем выдачу")
    public void sortResultsBy(TrainTicketsResultsPage.SortBy trainTicketsSortBy) {
        switch (trainTicketsSortBy) {
            case PRICE:
                trainTicketsResultsPage.sortByPriceCheckbox.click();;
                break;
            case ARRIVAL_TIME:
                break;
            case DEPARTURE_TIME:
                break;
            case TOTAL_TIME:
                break;
        }
    }

    @Step("Получаем цены в выдаче")
    public ArrayList<Integer> getResultsPrices(){
        // тут нужен кодстайл как сплитить такие длинные конструкции, либо разбить конструкцию или отказаться от лямбды.
        // Все по соглашению команды. Обработку удаления нечисловых символов так же можно вынести в отдельный метод, например.
        // Все зависит от кодстайла. К тому же лямбды сложней дебажить если пойдет что-то не так. Ну и реплейс регуляркой
        // вызовет у не посвященного человека вопросы.

        ArrayList<Integer> ticketPrices = new ArrayList<>();
        trainTicketsResultsPage.abstractTicketPrice.texts()
                .forEach((ticketResult)
                        -> ticketPrices.add(Integer.valueOf(ticketResult.replaceAll("[^\\d.]", ""))));
        return ticketPrices;
    }

    @Step("Проверяем сортировку цен в выдаче")
    public void checkPriceSorting(ArrayList<Integer> ticketPrices){
        // в данном случае тест свалится на первом не соответствии и упустит остальные. Если захотим, можно эти ошибки
        // ассертов складывать в какую то коллекцию, и если она не пуста выбрасывать исключение, а в лог потом выводить
        // все ошибки накопленные за сравнение. Либо инициализировать soft asserts, если нужен более широкий функционал.
        // Мое мнение, что уже при первом свалившимся ассерте надо отбраковывать. Это ошибка проверяемого функционала.

        for (int i = 1; i < ticketPrices.size(); i++) {
            Assertions.assertTrue(ticketPrices.get(i) >= ticketPrices.get(i - 1),
                    "Sorting error. " + ticketPrices.get(i) + " not greater then " + ticketPrices.get(i - 1));
        }
    }

    // Вообще все ассёршены можно вынести так же в отдельный класс, если они однотипны,
    // который и будет заниматься исключительно ассёртами

    TrainTicketsPage trainTicketsPage = new TrainTicketsPage();
    TrainTicketsResultsPage trainTicketsResultsPage = new TrainTicketsResultsPage();
}
