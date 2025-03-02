package expert.os.books.ddd.chapter10.products.domain;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;


@Embeddable(Embeddable.EmbeddableType.GROUPING)
@JsonbVisibility(FieldVisibilityStrategy.class)
public record Manufacturer(@Column String name, @Column String address, @Column String contactNumber) {

}
