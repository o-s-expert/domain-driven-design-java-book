package expert.os.books.ddd.chapter04.domain.soccer;

import java.util.List;

//aggregate root
public class Team {

    private String name;

    //value
    private Color color;

    private Coach coach;

    //entity
    private List<Player> players;
}
