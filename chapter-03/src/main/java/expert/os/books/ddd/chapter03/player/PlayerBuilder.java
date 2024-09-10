package expert.os.books.ddd.chapter03.player;

import java.time.Year;
import java.util.Objects;

public class PlayerBuilder {

    private String name;

    private Year start;

    private Year end;

    private String email;

    private Position position;

    private int goal = 0;


    public PlayerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder start(Year start) {
        this.start = start;
        return this;
    }

    public PlayerBuilder end(Year end) {
        this.end = end;
        return this;
    }

    public PlayerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public PlayerBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public PlayerBuilder goal(int goal) {
        this.goal = goal;
        return this;
    }

    public Player build() {
        Objects.requireNonNull(name, "Name is required");
        Objects.requireNonNull(start, "Start is required");
        Objects.requireNonNull(position, "Position is required");
        return new Player(name, start, end, email, position);
    }
}
