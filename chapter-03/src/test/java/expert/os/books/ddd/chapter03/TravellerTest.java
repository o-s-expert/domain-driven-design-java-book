package expert.os.books.ddd.chapter03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;


class TravellerTest {


    @Test
    void shouldCreateTraveller() {
        Traveller traveller = new Traveller();
        traveller.setPassportNumber("123456");
        traveller.setName("John Doe");
        traveller.setCountries(Set.of("USA", "UK"));

        Assertions.assertThat(traveller.getPassportNumber()).isEqualTo("123456");
        Assertions.assertThat(traveller.getName()).isEqualTo("John Doe");
        Assertions.assertThat(traveller.getCountries()).contains("USA", "UK");
    }

    @Test
    void breakingApiContract() {
        Traveller traveller = new Traveller();
        traveller.setPassportNumber("123456");
        traveller.setName("John Doe");
        traveller.setCountries(Set.of("USA", "UK"));
        traveller.setCountries(null);

        Assertions.assertThatThrownBy(() -> traveller.getCountries().contains("USA"))
                .isInstanceOf(NullPointerException.class);
    }

}
