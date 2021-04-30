package pages;

import com.codeborne.selenide.SelenideElement;
import elements.OverAllMenu;

import static com.codeborne.selenide.Selenide.$x;

public class AviaTicketsPage {

    public OverAllMenu overAllMenu = new OverAllMenu();
    // можно было бы отнаследоваться, но раз используем page elements, то части страниц с однотипными элементами
    // предпочитаю вставлять как поля класса. Но если в перспективе будет не удобно, можно через интерфейсы вынести
}
