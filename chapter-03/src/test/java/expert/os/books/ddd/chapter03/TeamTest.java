package expert.os.books.ddd.chapter03;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    void shouldCreateTeamSuccessfully() {
        // Act
        Team team = Team.create("Bahia", "BAH");

        // Assert using SoftAssertions with assertSoftly
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(team.getName()).isEqualTo("Bahia");
            softly.assertThat(team.getFifaCode()).isEqualTo("BAH");
            softly.assertThat(team.getPlayers()).isEmpty();
        });
    }

    @Test
    void shouldThrowExceptionWhenAddingNullPlayer() {
        // Arrange
        Team team = Team.create("Bahia", "BAH");

        // Act & Assert using SoftAssertions with assertSoftly
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> team.add(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("Player is required");
        });
    }

    @Test
    void shouldAddPlayerToTeamSuccessfully() {
        // Arrange
        Team team = Team.create("Bahia", "BAH");
        Player player = new Player();

        // Act
        team.add(player);

        // Assert using SoftAssertions with assertSoftly
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(team.getPlayers()).hasSize(1);
            softly.assertThat(team.getPlayers()).containsExactly(player);
        });
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicatePlayer() {
        // Arrange
        Team team = Team.create("Bahia", "BAH");
        Player player = new Player();
        team.add(player);

        // Act & Assert using SoftAssertions with assertSoftly
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> team.add(player))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Player already exists");
            softly.assertThat(team.getPlayers()).hasSize(1); // Verify no additional player added
        });
    }

    @Test
    void shouldThrowExceptionWhenTeamIsFull() {
        // Arrange
        Team team = Team.create("Bahia", "BAH");

        // Act
        for (int i = 0; i < 23; i++) {
            team.add(new Player());
        }

        // Act & Assert using SoftAssertions with assertSoftly
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> team.add(new Player()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Team is full");
            softly.assertThat(team.getPlayers()).hasSize(23); // Verify team size remains at 23
        });
    }
}
