package expert.os.books.ddd;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface DeveloperResource extends PanacheEntityResource<Developer, Long> {
}