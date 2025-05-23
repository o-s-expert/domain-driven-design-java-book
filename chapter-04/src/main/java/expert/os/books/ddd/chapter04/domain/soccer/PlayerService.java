package expert.os.books.ddd.chapter04.domain.soccer;

public interface PlayerService {


    void hire(Player player, Money salary, Team team);

    void fire(Player player);

    void transfer(Player player, Team team);

    void assignCoach(Player player, Coach coach);

    void assignCaptain(Player player, Player captain, Team team);

}
