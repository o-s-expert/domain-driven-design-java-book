package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Schema
@JsonbVisibility(FieldVisibilityStrategy.class)
public class ProductDTO {

    private String id;

    private String name;

    private ManufacturerDTO manufacturer;

    private List<String> tags;

    private Set<CategoryDTO> categories;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDTO product = (ProductDTO) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer=" + manufacturer +
                ", tags=" + tags +
                ", categories=" + categories +
                '}';
    }
}
