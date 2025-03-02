package expert.os.books.ddd.chapter10.products.application;


import expert.os.books.ddd.chapter10.products.domain.Product;
import expert.os.books.ddd.chapter10.products.domain.ProductRepository;
import jakarta.data.Order;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    private final ProductRepository repository;

    private final ProductMapper mapper;

    @Deprecated
    public ProductService() {
        this(null, null);
    }

    @Inject
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<ProductResponse> findAll(PageRequest request, Order<Product> order) {
        LOGGER.info("Finding all products in the page request: " + request);
        var products = repository.findAll(request, order).content();
        LOGGER.info("Found " + products.size() + " products in the page request: " + request);
        LOGGER.info("Found: " + products);
        List<ProductResponse> responses = products.stream().map(mapper::toDTO).map(ProductResponse::of).toList();
        LOGGER.info("Found " + responses.size() + " products");
        LOGGER.info("responses: " + responses);
        return responses;
    }

    public ProductResponse save(ProductRequest request) {
        ProductDTO product = request.getProduct();
        LOGGER.fine(() -> "Saving product: " + product);
        var entity = repository.save(mapper.toEntity(product));
        return ProductResponse.of(mapper.toDTO(entity));
    }

    public void deleteById(String id) {
        LOGGER.info("Deleting product with id: " + id);
        repository.deleteById(id);
    }

    public Optional<ProductResponse> findById(String id) {
        LOGGER.info("Finding product with id: " + id);
        return repository.findById(id).map(mapper::toDTO).map(ProductResponse::of);
    }
}
