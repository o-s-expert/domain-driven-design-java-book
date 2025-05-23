package expert.os.books.ddd.chapter04.domain.soccer;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PlayerBuilder {
    private UUID uuid;
    private String name;
    private String city;
    private Money salary;

    public PlayerBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public PlayerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public PlayerBuilder salary(Money salary) {
        this.salary = salary;
        return this;
    }

    public Player build() {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(city, "city must not be null");
        Objects.requireNonNull(salary, "salary must not be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (city.isBlank()) {
            throw new IllegalArgumentException("city must not be blank");
        }
        if (salary.amount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("salary must not be negative");
        }
        return new Player(uuid, name, city, salary);
    }
}