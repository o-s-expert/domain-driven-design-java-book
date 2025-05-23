package expert.os.books.ddd.chapter04.domain.jmolecules;


import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Card {

    @Identity
    private String id;
}
