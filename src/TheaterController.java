import enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterController {
    Map<City, List<Theater>> cityVsTheaters;
    List<Theater> theaters;

    public TheaterController() {
        cityVsTheaters = new HashMap<>();
        theaters = new ArrayList<>();
    }

    public void addTheater(Theater theater, City city) {
        cityVsTheaters.computeIfAbsent(city, key -> new ArrayList<>()).add(theater);
        theaters.add(theater);
    }




    public Map<Theater, List<Shows>> getAllShows(Movie movie, City city) {
        List<Theater> theaters = cityVsTheaters.get(city);
        Map<Theater, List<Shows> > ans = new HashMap<>();
        for (Theater theater : theaters) {
            List<Shows> shows = theater.getShows();
            for (Shows show : shows) {
                if (show.getMovie().equals(movie)) {
                    ans.computeIfAbsent(theater, key -> new ArrayList<>()).add(show);
                }
            }
        }
      return ans;
    }
}




