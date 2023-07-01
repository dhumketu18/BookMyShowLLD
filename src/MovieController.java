import enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {

    private Map<City, List<Movie>> cityVsMovies;
    private final List<Movie> movies;

    public  MovieController() {
        cityVsMovies = new HashMap<>();
        movies = new ArrayList<>();
    }

    public void add(Movie movie, City city) {
        cityVsMovies.computeIfAbsent(city, key -> new ArrayList<>()).add(movie);
        movies.add(movie);
    }

    Movie getMovieByName(String movieName) {

        for(Movie movie : movies) {
            if((movie.getName()).equals(movieName)) {
                return movie;
            }
        }
        return null;
    }


    List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.get(city);
    }

}
