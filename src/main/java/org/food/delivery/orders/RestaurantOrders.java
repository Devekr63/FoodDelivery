package org.food.delivery.orders;

import org.food.delivery.partners.Partner;

import java.util.HashMap;
import java.util.Set;

public class RestaurantOrders {
    private HashMap<Partner, Set<Partner>> orders;

    public RestaurantOrders(HashMap<Partner, Set<Partner>> orders) {
        this.orders = orders;
    }

    public HashMap<Partner, Set<Partner>> getOrders() {
        return orders;
    }
}
