import enums.City;

import java.util.List;

public class Theater {
    int id;
    List<Shows> shows;
    List<Audi> audis;

    City city;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Shows> getShows() {
        return shows;
    }

    public void setShows(List<Shows> shows) {
        this.shows = shows;
    }

    public List<Audi> getAudis() {
        return audis;
    }

    public void setAudis(List<Audi> audis) {
        this.audis = audis;
    }
}
