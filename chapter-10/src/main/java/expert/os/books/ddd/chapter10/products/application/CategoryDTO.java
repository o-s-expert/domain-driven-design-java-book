package expert.os.books.ddd.chapter10.products.application;


import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;

import java.util.Objects;

@JsonbVisibility(FieldVisibilityStrategy.class)
public class CategoryDTO {

    private String name;

    private String description;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
