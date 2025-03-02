package expert.os.books.ddd.chapter10.products;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ProductRepository extends BasicRepository<Product, String> {
}
