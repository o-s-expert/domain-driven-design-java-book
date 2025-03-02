package expert.os.books.ddd;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Programmer extends PanacheEntity {

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String language;
    @Column
    private String city;
}

