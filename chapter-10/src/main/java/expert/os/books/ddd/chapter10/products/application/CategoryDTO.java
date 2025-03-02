package expert.os.books.ddd.chapter10.products.application;


import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;

@JsonbVisibility(FieldVisibilityStrategy.class)
public class CategoryDTO {

    private String name;

    private String description;
}
