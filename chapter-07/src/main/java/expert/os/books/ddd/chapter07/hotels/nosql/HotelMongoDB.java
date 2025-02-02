package expert.os.books.ddd.chapter07.hotels.nosql;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import expert.os.books.ddd.chapter07.hotels.Hotel;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;


import java.util.Optional;

public class HotelMongoDB implements Hotel {

    private final MongoCollection<RoomNoSQL> roomCollection;
    private final NoSQLMapper mapper;

    public HotelMongoDB(MongoCollection<RoomNoSQL> roomCollection, NoSQLMapper mapper) {
        this.roomCollection = roomCollection;
        this.mapper = mapper;
    }

    @Override
    public Room checkIn(Room room) {
        RoomNoSQL roomNoSQL = mapper.toEntity(room);
        Bson filter = eq("number", room.getNumber());

        roomCollection.updateOne(filter, Updates.set("guest", roomNoSQL.getGuest()), new UpdateOptions().upsert(true));
        return mapper.toDomain(roomNoSQL);
    }

    @Override
    public void checkOut(Room room) {
        Bson filter = eq("number", room.getNumber());
        roomCollection.updateOne(filter, Updates.unset("guest"));
    }

    @Override
    public Optional<Room> reservation(String number) {
        RoomNoSQL roomNoSQL = roomCollection.find(eq("number", Long.parseLong(number))).first();
        return Optional.ofNullable(roomNoSQL).map(mapper::toDomain);
    }

    @Override
    public Long countBy() {
        return roomCollection.countDocuments();
    }

    @Override
    public Optional<Room> findEmptyRoom() {
        RoomNoSQL emptyRoom = roomCollection.find(eq("guest", null)).first();
        return Optional.ofNullable(emptyRoom).map(mapper::toDomain);
    }
}
