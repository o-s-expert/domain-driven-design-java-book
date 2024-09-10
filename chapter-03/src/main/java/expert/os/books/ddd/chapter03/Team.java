package expert.os.books.ddd.chapter03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Team {

    private final String fifaCode;

    private final String name;

    private final List<Player> players;

    private Team(String name, String fifaCode) {
        this.name = name;
        this.fifaCode = fifaCode;
        this.players = new ArrayList<>();
    }

    public String getFifaCode() {
        return fifaCode;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void add(Player player) {
        Objects.requireNonNull(player, "Player is required");
        if (players.contains(player)) {
            throw new IllegalArgumentException("Player already exists");
        } else if (players.size() >= 23) {
            throw new IllegalArgumentException("Team is full");
        }
        players.add(player);
    }

    public static Team create(String name, String fifaCode) {
        Objects.requireNonNull(name, "Name is required");
        Objects.requireNonNull(fifaCode, "FIFA code is required");
        return new Team(name, fifaCode);
    }
}
