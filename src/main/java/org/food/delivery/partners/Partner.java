package org.food.delivery.partners;

import org.food.delivery.location.GeoLocation;

public class Partner {
    private String name;
    private GeoLocation location;

    public Partner(String name, GeoLocation location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public GeoLocation getLocation() {
        return location;
    }
}
