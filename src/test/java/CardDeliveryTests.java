import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTests {

    @Test
    public void shouldHappyTestMeetingIn3Days() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Семенов Олег");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.visible.exactText("Встреча успешно забронирована на " + meetingDate));

    }

    @Test
    public void shouldHappyTestMeetingIn4Days() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Петров Иван");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.visible.exactText("Встреча успешно забронирована на " + meetingDate));

    }

    @Test
    public void shouldHappyTestNotCity() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Подольск");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Петров Иван");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(".input__sub").shouldBe(visible)
                .shouldHave(Condition.visible.exactText("Доставка в выбранный город недоступна"));

    }
    @Test
    public void shouldHappyTestNameLatin() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Emil Karlson");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(By.xpath("//*[contains(text()," +
                "'Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы.')]")).shouldBe(visible)
                .shouldHave(Condition.visible.exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldHappyTestNameNumber() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Петр1");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(By.xpath("//*[contains(text()," +
                "'Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы.')]")).shouldBe(visible)
                .shouldHave(Condition.visible.exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldHappyTestNamePunctuationMarks() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String meetingDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Калашников Егор.");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(By.xpath("//*[contains(text()," +
                "'Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы.')]")).shouldBe(visible)
                .shouldHave(Condition.visible.exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldHappyTestMeetingIn7Days() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ро");
        // $$(".menu-item__control").findBy(Condition.text("Москва")).click();
        $(By.xpath("//span[contains(text(),'Ростов-на-Дону')]")).shouldBe(Condition.visible).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();

        if (!generateDate(3, "MM").equals(generateDate(7, "MM"))) {
            $(".calendar__arrow_direction_right").click();
        }
        $$("[data-day]").findBy(Condition.text(generateDate(7, "d"))).click();
        $("[data-test-id='name'] input").setValue("Семенов Олег");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.visible.exactText("Встреча успешно забронирована на " + planningDate));
    }
}