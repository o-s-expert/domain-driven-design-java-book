package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Schema(description = "Represents a product.")
@JsonbVisibility(FieldVisibilityStrategy.class)
public class ProductDTO {

    @Schema(description = "Unique identifier for the product.")
    private String id;

    @Schema(description = "Name of the product.")
    private String name;

    @Schema(description = "Manufacturer details of the product.")
    private ManufacturerDTO manufacturer;

    @Schema(description = "Tags associated with the product.")
    private List<String> tags;

    @Schema(description = "Categories the product belongs to.")
    private Set<CategoryDTO> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

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
