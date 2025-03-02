package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.domain.Product;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/products")
@Tag(name = "Products", description = "Operations related to product management")
public class ProductResource {

    private static final Order<Product> ORDER = Order.by(Sort.asc("name"));

    private static final Logger LOGGER = Logger.getLogger(ProductResource.class.getName());

    private final ProductService service;

    @Inject
    public ProductResource(ProductService service) {
        this.service = service;
    }

    @Deprecated
    public ProductResource() {
        this(null);
    }

    @GET
    @Operation(summary = "Retrieve a paginated list of products",
            description = "Fetches products based on page number and size")
    @APIResponse(responseCode = "200",
            description = "Successfully retrieved list of products",
            content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    public List<ProductDTO> get(
            @Parameter(description = "Page number", example = "1")
            @QueryParam("page") @DefaultValue("1") int page,

            @Parameter(description = "Size of the page", example = "10")
            @QueryParam("size") @DefaultValue("10") int size) {

        LOGGER.info("The page number is: " + page + " and the size is: " + size);
        var request = PageRequest.ofPage(page).size(size);
        return service.findAll(request, ORDER);
    }

    @POST
    @Operation(summary = "Create a new product",
            description = "Inserts a new product into the system")
    @APIResponse(responseCode = "201",
            description = "Product created successfully",
            content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    public ProductDTO insert(
            @Parameter(description = "Product details to be saved", required = true)
            ProductDTO product) {

        LOGGER.info("The product will be saved: " + product);
        return service.save(product);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a product by ID",
            description = "Removes a product from the system based on its ID")
    @APIResponse(responseCode = "204", description = "Product deleted successfully")
    @APIResponse(responseCode = "404", description = "Product not found")
    public void delete(
            @Parameter(description = "Product ID to be deleted", required = true, example = "12345")
            @PathParam("id") String id) {

        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find a product by ID",
            description = "Fetches details of a specific product using its ID")
    @APIResponse(responseCode = "200",
            description = "Product retrieved successfully",
            content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "404", description = "Product not found")
    public ProductDTO findById(
            @Parameter(description = "Product ID to search for", required = true, example = "12345")
            @PathParam("id") String id) {

        return service.findById(id)
                .orElseThrow(() -> new WebApplicationException("Product not found, with id: " + id, Response.Status.NOT_FOUND));
    }
}
