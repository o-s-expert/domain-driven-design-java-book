package expert.os.books.ddd.chapter10.products;

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

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/products")
public class ProductResource {

    private static final Order<Product> ORDER = Order.by(Sort.asc("name"));

    private static final Logger LOGGER = Logger.getLogger(ProductResource.class.getName());

    private final ProductRepository repository;

    @Inject
    public ProductResource(ProductRepository repository) {
        this.repository = repository;
    }

    @Deprecated
    public ProductResource() {
        this(null);
    }

    @GET
    public List<Product> get(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        LOGGER.info("The page number is: " + page + " and the size is: " + size);
        var request = PageRequest.ofPage(page).size(size);
        return repository.findAll(request, ORDER).content();
    }

    @POST
    public Product insert(Product product) {
        LOGGER.info("The product will be saved: " + product);
        return repository.save(product);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        repository.deleteById(id);
    }

    @GET
    @Path("{id}")
    public Product findById(@PathParam("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new WebApplicationException("Product not found, with id: " + id, Response.Status.NOT_FOUND));
    }
}
