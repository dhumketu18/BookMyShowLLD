import java.util.ArrayList;
import java.util.List;

public class Shows {
    int id;
    Movie movie;
    List<Integer> bookingIds = new ArrayList<>();
    Audi audi;

    public List<Integer> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Integer> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public Audi getAudi() {
        return audi;
    }

    public void setAudi(Audi audi) {
        this.audi = audi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
