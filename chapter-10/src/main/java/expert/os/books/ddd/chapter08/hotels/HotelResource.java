package expert.os.books.ddd.chapter08.hotels;

import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Logger;

@Path("/hotels")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HotelResource {

    private static final Logger LOGGER = Logger.getLogger(HotelResource.class.getName());

    private final Hotel hotel;

    @Inject
    public HotelResource(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelResource() {
        this(null);
    }

    @GET
    public List<Room> rooms(@QueryParam("page") @DefaultValue("1") int page,
                            @QueryParam("size") @DefaultValue("1") int size) {
        LOGGER.info("Finding rooms page: " + page + " size: " + size);
        var pageRequest = PageRequest.ofPage(page).size(size);
        var rooms = hotel.findBy(pageRequest).content();
        LOGGER.info("Found rooms: " + rooms.size());
        return rooms;
    }

    @GET
    @Path("/{number}")
    public Room reservation(@PathParam("number") String number) {
        LOGGER.info("Finding reservation: " + number);
        return hotel.reservation(number)
                .orElseThrow(() -> new WebApplicationException("Room not found", Response.Status.NOT_FOUND));
    }

    @PUT
    public Room checkIn(Room room) {
        LOGGER.info("Check in: " + room);
        return hotel.checkIn(room);
    }

    @DELETE
    @Path("/{number}")
    public void checkOut(@PathParam("number") String number) {
        LOGGER.info("Check out: " + number);
        var room = hotel.reservation(number)
                .orElseThrow(() -> new WebApplicationException("Room not found", Response.Status.NOT_FOUND));
        hotel.checkOut(room);
    }
}
