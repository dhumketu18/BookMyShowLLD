import enums.City;
import enums.SeatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {
     TheaterController theaterController;
     MovieController movieController;
    public BookMyShow() {
        theaterController = new TheaterController();
        movieController = new MovieController();
    }

    private void initialize() {
        createMovies();
        createTheaters();
    }

    private void createMovies() {
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setName("BAAHUBALI");
        movie1.setTimeDuration(3);

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setName("AVENGERS");
        movie2.setTimeDuration(4);

        movieController.add(movie1, City.Bengaluru);
        movieController.add(movie2, City.Delhi);
    }
    private void createTheaters() {

        Theater inoxtheater = new Theater();
        inoxtheater.setAudis(createAudis());
        inoxtheater.setId(1);
        List<Shows> shows = new ArrayList<>();
        shows.add(createShows(0, inoxtheater.getAudis().get(0), movieController.getMovieByName("BAAHUBALI")));
        inoxtheater.setShows(shows);

        theaterController.addTheater(inoxtheater, City.Bengaluru);

        Theater pvrtheater = new Theater();
        pvrtheater.setAudis(createAudis());
        pvrtheater.setId(1);
        List<Shows> shows2 = new ArrayList<>();
        shows2.add(createShows(1, inoxtheater.getAudis().get(0), movieController.getMovieByName("BAAHUBALI")));
        pvrtheater.setShows(shows2);

        theaterController.addTheater(pvrtheater, City.Delhi);
    }

    private Shows createShows(int id, Audi audi, Movie movie) {
        Shows show = new Shows();
        show.setAudi(audi);
        show.setId(id);
        show.setMovie(movie);
        return show;
    }

    private List<Audi> createAudis() {
        List<Audi> ans = new ArrayList<>();
        Audi audi1 = new Audi();
        audi1.setSeats(createSeats());
        audi1.setId(1);
        Audi audi2 = new Audi();
        audi2.setSeats(createSeats());
        audi2.setId(2);
        ans.add(audi1);
        ans.add(audi2);
        return ans;
    }

    private List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        //first 50 DeLUXE
        for (int i = 0; i < 50; i++) {
            Seat seat = new Seat();
            seat.setSeatType(SeatType.DELUXE);
            seat.setPrice(1000);
            seat.setId(i + 1);
            seats.add(seat);
        }
        for (int i = 50; i < 99; i++) {
            Seat seat = new Seat();
            seat.setSeatType(SeatType.BALCONY);
            seat.setPrice(2000);
            seat.setId(i + 1);
            seats.add(seat);
        }
        return seats;
    }
    public static void main(String[] args) {

        BookMyShow bookMyShow = new BookMyShow();

        bookMyShow.initialize();

        //user1
        bookMyShow.createBooking(City.Bengaluru, "BAAHUBALI");
        //user2
        bookMyShow.createBooking(City.Bengaluru, "BAAHUBALI");


    }
    private void createBooking(City userCity, String movieName) {


        //1. search movie by my location
        List<Movie> movies = movieController.getMoviesByCity(userCity);

        //2. select the movie which you want to see. i want to see Baahubali
        Movie interestedMovie = null;
        for (Movie movie : movies) {

            if ((movie.getName()).equals(movieName)) {
                interestedMovie = movie;
            }
        }

        //3. get all show of this movie in Bangalore location
        Map<Theater, List<Shows>> showsTheatreWise = theaterController.getAllShows(interestedMovie, userCity);

        //4. select the particular show user is interested in
        Map.Entry<Theater,List<Shows>> entry = showsTheatreWise.entrySet().iterator().next();
        List<Shows> runningShows = entry.getValue();
        Shows interestedShow = runningShows.get(0);

        //5. select the seat
        int seatNumber = 30;
        List<Integer> bookedSeats = interestedShow.getBookingIds();
        if(!bookedSeats.contains(seatNumber)){
            bookedSeats.add(seatNumber);
            //startPayment
            Booking booking = new Booking();
            List<Seat> myBookedSeats = new ArrayList<>();
            for(Seat screenSeat : interestedShow.getAudi().getSeats()) {
                if(screenSeat.getId() == seatNumber) {
                    myBookedSeats.add(screenSeat);
                }
            }
            booking.setSeats(myBookedSeats);
        } else {
            //throw exception
            System.out.println("seat already booked, try again");
            return;
        }

        System.out.println("BOOKING SUCCESSFUL");
    }



}