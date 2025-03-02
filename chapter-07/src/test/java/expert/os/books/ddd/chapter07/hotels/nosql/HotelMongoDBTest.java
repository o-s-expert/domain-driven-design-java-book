package expert.os.books.ddd.chapter07.hotels.nosql;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.MongodStarter;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.embed.process.runtime.Network;
import de.flapdoodle.reverse.TransitionWalker;
import expert.os.books.ddd.chapter07.hotels.Guest;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.assertj.core.api.SoftAssertions;

import java.util.Optional;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Disabled("This test is disabled because it requires a running MongoDB instance")
class HotelMongoDBTest {

    private static TransitionWalker.ReachedState<RunningMongodProcess> runningMongod;
    private MongoClient mongoClient;
    private HotelMongoDB hotelMongoDB;

    @BeforeAll
    static void setUpClass() {
        runningMongod = Mongod.instance().start(Version.Main.PRODUCTION);
    }

    @BeforeEach
    void setUp() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider)
        );

        String connectionString = "mongodb://" + runningMongod.current().getServerAddress().getHost()
                + ":" + runningMongod.current().getServerAddress().getPort();

        mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build()
        );
        MongoDatabase database = mongoClient.getDatabase("hotel").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<RoomNoSQL> rooms = database.getCollection("rooms", RoomNoSQL.class);
        NoSQLMapper mapper = Mappers.getMapper(NoSQLMapper.class);
        hotelMongoDB = new HotelMongoDB(rooms, mapper);
    }


    @AfterEach
    void tearDown() {
        mongoClient.getDatabase("hotel").drop();
        mongoClient.close();
    }

    @AfterAll
    static void tearDownClass() {
        if (runningMongod != null) {
            runningMongod.close();
        }
    }

    @Test
    void shouldCheckInGuestToRoom() {
        Room room = new Room(101, new Guest("12345", "John Doe"));

        Room checkedInRoom = hotelMongoDB.checkIn(room);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(checkedInRoom).isNotNull();
        softly.assertThat(checkedInRoom.getGuest()).isNotNull();
        softly.assertThat(checkedInRoom.getGuest().documentNumber()).isEqualTo("12345");
        softly.assertAll();
    }

    @Test
    void shouldCheckOutGuestFromRoom() {
        Room room = new Room(101, new Guest("12345", "John Doe"));
        hotelMongoDB.checkIn(room);

        hotelMongoDB.checkOut(room);
        Optional<Room> result = hotelMongoDB.reservation("101");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result).isPresent();
        softly.assertThat(result.get().getGuest()).isNull();
        softly.assertAll();
    }

    @Test
    void shouldFindEmptyRoom() {
        hotelMongoDB.checkIn(new Room(101, null));

        Optional<Room> emptyRoom = hotelMongoDB.findEmptyRoom();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(emptyRoom).isPresent();
        softly.assertThat(emptyRoom.get().getNumber()).isEqualTo(101L);
        softly.assertAll();
    }

    @Test
    void shouldCountRooms() {
        hotelMongoDB.checkIn(new Room(101, new Guest("12345", "John Doe")));
        hotelMongoDB.checkIn(new Room(102, null));

        long roomCount = hotelMongoDB.countBy();

        assertThat(roomCount).isEqualTo(2L);
    }

    @Test
    void shouldMakeReservation() {
        hotelMongoDB.checkIn(new Room(101, new Guest("12345", "John Doe")));

        Optional<Room> reservedRoom = hotelMongoDB.reservation("101");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(reservedRoom).isPresent();
        softly.assertThat(reservedRoom.get().getGuest()).isNotNull();
        softly.assertThat(reservedRoom.get().getGuest().documentNumber()).isEqualTo("12345");
        softly.assertAll();
    }
}
