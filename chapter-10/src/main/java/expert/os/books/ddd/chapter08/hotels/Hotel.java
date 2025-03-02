package expert.os.books.ddd.chapter08.hotels;

import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.Optional;

@Repository
public interface Hotel {

    @Save
    Room checkIn(Room room);

    @Delete
    void checkOut(Room room);

    @Find
    Optional<Room> reservation(@By(expert.os.books.ddd.chapter08.hotels._Room.NUMBER) String number);

    Page<Room> findBy(PageRequest pageRequest);
}
