import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class MapDeliveryTest {

    String getDate(int currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        return dateFormat.format(cal.getTime());
    }

    @Test
    // Full Positive Test
    void fullPositiveTest() {
        Configuration.timeout = 60000;

        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Хабаровск"); // Устанавливаем правильный город
        $("[placeholder='Дата встречи']").setValue(getDate(2)); // вводим правильный формат даты
        $("[name='name']").setValue("Иванов Иван"); // ВВодим правильные ИФ
        $("[name='phone']").setValue("+70009998877"); // Вводим правильным номер телефона
        $("[data-test-id=agreement] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible);
    }
}
