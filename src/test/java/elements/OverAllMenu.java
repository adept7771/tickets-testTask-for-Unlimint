package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class OverAllMenu {

    public SelenideElement
            trainTicketsButton =  $x("//a[@href='/trains/']"),
            planeTicketsButton =  $x("//a[@href='/avia/']");

}
