package expert.os.books.ddd.chapter03.player;

import java.time.Year;
import java.util.Objects;

public class PlayerBuilder implements FluentPlayer,
        FluentPlayer.PlayerEnd, FluentPlayer.PlayerEmail, FluentPlayer.PlayerPosition {

    static final Year SOCCER_BORN = Year.of(1863);

    PlayerBuilder(String name) {
        this.name = name;
    }

    private String name;

    private Year start;

    private Year end;

    private String email;

    private Position position;

    @Override
    public PlayerEnd start(Year start) {
        Objects.requireNonNull(start, "start is required");
        if (Year.now().isBefore(start)) {
            throw new IllegalArgumentException("you cannot start in the future");
        }
        if (SOCCER_BORN.isAfter(start)) {
            throw new IllegalArgumentException("Soccer was not born on this time");
        }
        this.start = start;
        return this;
    }

    @Override
    public PlayerPosition end(Year end) {
        Objects.requireNonNull(end, "end is required");

        if (start != null && start.isAfter(end)) {
            throw new IllegalArgumentException("the last year of a player must be equal or higher than the start.");
        }

        if (SOCCER_BORN.isAfter(end)) {
            throw new IllegalArgumentException("Soccer was not born on this time");
        }
        this.end = end;
        return this;
    }

    @Override
    public Player email(String email) {
        this.email = Objects.requireNonNull(email, "email is required");
        return new Player(name, start, end, email, position);
    }

    @Override
    public PlayerEmail position(Position position) {
        Objects.requireNonNull(position, "position is required");
        this.position = position;
        return this;
    }

    @Override
    public Player build() {
        return new Player(name, start, end, email, position);
    }

}
