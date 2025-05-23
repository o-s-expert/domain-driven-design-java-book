package expert.os.books.ddd.chapter04.domain.cars;

import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface Garage {

    void park(Car car);

    void unpark(Car car);

    Car findCarByPlate(String plate);
}
