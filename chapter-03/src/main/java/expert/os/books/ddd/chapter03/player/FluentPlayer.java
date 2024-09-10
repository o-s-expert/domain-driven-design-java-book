package expert.os.books.ddd.chapter03.player;

import java.time.Year;

public interface FluentPlayer {

    PlayerEnd start(Year start);

    interface  PlayerEnd {
        PlayerPosition end(Year start);
    }

    interface PlayerPosition {
        PlayerEmail position(Position position);
    }


    interface PlayerEmail {
        Player email(String email);

        Player build();

    }

}
