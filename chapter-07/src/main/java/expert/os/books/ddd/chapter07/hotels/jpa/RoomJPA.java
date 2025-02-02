package expert.os.books.ddd.chapter07.hotels.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
class RoomJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private GuestJPA guest;

    RoomJPA(Long room, GuestJPA guestJPA) {
        this.number = room;
        this.guest = guestJPA;
    }

    public RoomJPA() {
    }


    public void setGuest(GuestJPA guest) {
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

    public GuestJPA getGuest() {
        return guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomJPA roomJPA = (RoomJPA) o;
        return Objects.equals(number, roomJPA.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
