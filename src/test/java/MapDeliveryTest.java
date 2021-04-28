import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class MapDeliveryTest {

    String getDate(int daysFromCurrentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysFromCurrentDate);
        return dateFormat.format(cal.getTime());
    }

    @Test
    // Full Positive Test
    void fullPositiveTest() {
        // Configuration.timeout = 60000;

        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Хабаровск");
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(getDate(5));
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+70009998877");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);
    }
}
