package expert.os.books.ddd.chapter10.products;

import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;
import org.soujava.samples.mongodb.products.infra.FieldVisibilityStrategy;


@Embeddable(Embeddable.EmbeddableType.GROUPING)
@JsonbVisibility(FieldVisibilityStrategy.class)
public record Manufacturer(@Column String name, @Column String address, @Column String contactNumber) {

}
