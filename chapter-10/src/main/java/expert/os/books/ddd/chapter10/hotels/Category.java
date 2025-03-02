package expert.os.books.ddd.chapter10.hotels;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;

@Embeddable(Embeddable.EmbeddableType.GROUPING)
public record Category(@Column String name, @Column String description) {
}