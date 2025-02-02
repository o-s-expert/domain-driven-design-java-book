package expert.os.books.ddd.chapter07.hotels.nosql;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class RoomNoSQL {

    @BsonProperty("number")
    private Long number;

    @BsonProperty("guest")
    private GuestNoSQL guest;

    RoomNoSQL(Long room, GuestNoSQL guestNoSQL) {
        this.number = room;
        this.guest = guestNoSQL;
    }

    public RoomNoSQL() {
    }


    public void setGuest(GuestNoSQL guest) {
        this.guest = guest;
    }

    public void cleanRoom() {
        this.guest = null;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public GuestNoSQL getGuest() {
        return guest;
    }
}
