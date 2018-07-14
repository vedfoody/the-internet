package entities;

/**
 * Created by thuan on 09/01/2017.
 */
public class Geolocation {
    private String status;
    private Float accuracy;
    private Location location;

    public Geolocation(String status, Float accuracy, Location location) {
        this.status = status;
        this.accuracy = accuracy;
        this.location = location;
    }
}
