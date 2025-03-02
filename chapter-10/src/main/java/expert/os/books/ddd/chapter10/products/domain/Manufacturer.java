package expert.os.books.ddd.chapter10.products.domain;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;


@Embeddable(Embeddable.EmbeddableType.GROUPING)
public record Manufacturer(@Column String name, @Column String address, @Column String contactNumber) {

}
