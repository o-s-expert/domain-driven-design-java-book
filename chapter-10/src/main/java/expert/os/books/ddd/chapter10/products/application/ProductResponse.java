package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Response object for retrieving product details.")
@JsonbVisibility(FieldVisibilityStrategy.class)
public class ProductResponse {

  @Schema(description = "Product details included in the response.")
  private ProductDTO product;


  @Override
  public String toString() {
    return "ProductResponse{" +
            "product=" + product +
            '}';
  }

  public static ProductResponse of(ProductDTO product) {
    var response = new ProductResponse();
    response.product = product;
    return response;
  }
}