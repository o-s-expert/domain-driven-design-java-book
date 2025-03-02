package expert.os.books.ddd.chapter10.products.application;


import expert.os.books.ddd.chapter10.products.domain.Product;
import expert.os.books.ddd.chapter10.products.domain.ProductRepository;
import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    private final ProductRepository repository;

    @Deprecated
    public ProductService() {
        this(null);
    }

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<ProductResponse> findAll(PageRequest request, Order<Product> order) {
        return null;
    }

    public ProductResponse save(ProductRequest product) {
        return null;
    }

    public void deleteById(String id) {

    }

    public Optional<ProductResponse> findById(String id) {
        return null;
    }
}
