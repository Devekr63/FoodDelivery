package org.food.delivery.partners;

import org.food.delivery.location.GeoLocation;

public class Restaurant extends Partner {

    private Double cookingTime;

    public Restaurant(String name, GeoLocation location, Double cookingTime) {
        super(name, location);
        this.cookingTime = cookingTime;
    }

    public Double getCookingTime() {
        return cookingTime;
    }
}
