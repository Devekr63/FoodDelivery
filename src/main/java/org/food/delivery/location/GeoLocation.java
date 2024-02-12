package org.food.delivery.location;

public class GeoLocation {
    Double latitude;
    Double longitude;

    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double distanceFromLocation(GeoLocation location) {
        Double radius = 6371.0;
        Double delta_phi = Math.toRadians(latitude - location.latitude);
        Double delta_lambda = Math.toRadians(longitude - location.longitude);

        Double phi_1 = Math.toRadians(latitude);
        Double phi_2 = Math.toRadians(location.latitude);

        Double a = Math.pow(Math.sin(delta_phi / 2), 2) +
                Math.pow(Math.sin(delta_lambda / 2), 2) *
                        Math.cos(phi_1) *
                        Math.cos(phi_2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (double) Math.round(radius * c * 1000d) / 1000d;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
