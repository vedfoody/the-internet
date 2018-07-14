package entities;

public class Location {
    private Float lat;
    private Float lng;

    public Location(Float lat, Float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLng() {
        return lng;
    }

    public Float getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location that = (Location) obj;
            if (this.lat.toString().equals(that.getLat().toString())
                    && this.lng.toString().equals(that.getLng().toString()))
                return true;
        }

        return false;
    }
}
