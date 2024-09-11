package expert.os.books.ddd.chapter03.player;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void shouldFluent() {
        Player player = Player.name("Ronaldo")
                .start(Year.of(2003))
                .end(Year.of(2020))
                .position(Position.FORWARD)
                .email("ronaldo@bahia.com");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(player.getName()).isEqualTo("Ronaldo");
            softly.assertThat(player.getStart()).isEqualTo(Year.of(2003));
            softly.assertThat(player.getEnd().orElseThrow()).isEqualTo(Year.of(2020));
            softly.assertThat(player.getPosition()).isEqualTo(Position.FORWARD);
            softly.assertThat(player.getEmail().orElseThrow()).isEqualTo("ronaldo@bahia.com");
        });

    }

    @Test
    void shouldBuilder() {
        Player player = Player.builder()
                .name("Ronaldo")
                .start(Year.of(2003))
                .end(Year.of(2020))
                .position(Position.FORWARD)
                .email("ronaldo@bahia.com")
                .build();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(player.getName()).isEqualTo("Ronaldo");
            softly.assertThat(player.getStart()).isEqualTo(Year.of(2003));
            softly.assertThat(player.getEnd().orElseThrow()).isEqualTo(Year.of(2020));
            softly.assertThat(player.getPosition()).isEqualTo(Position.FORWARD);
            softly.assertThat(player.getEmail().orElseThrow()).isEqualTo("ronaldo@bahia.com");
        });
    }

}
