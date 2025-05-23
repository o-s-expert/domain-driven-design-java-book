package expert.os.books.ddd.chapter04.domain.soccer;

import java.util.UUID;


//entity is a class that has an identity
public class Player {

    //id is a UUID
    private UUID uuid;

    private String name;

    private String city;

    private Money salary;

    Player(UUID uuid, String name, String city, Money salary) {
        this.uuid = uuid;
        this.name = name;
        this.city = city;
        this.salary = salary;
    }
}
