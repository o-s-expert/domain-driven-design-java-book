package expert.os.books.ddd.chapter10.products.domain;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ProductRepository extends BasicRepository<Product, String> {
}
