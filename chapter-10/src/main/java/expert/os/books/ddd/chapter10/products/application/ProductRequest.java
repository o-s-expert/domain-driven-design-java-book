package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request object for creating or updating a product.")
@JsonbVisibility(FieldVisibilityStrategy.class)
public class ProductRequest {

    @Schema(description = "Product details for the request.")
    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }
}