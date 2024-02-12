package org.food.delivery.orders;

import org.food.delivery.partners.Partner;

import java.util.HashMap;

public class CustomerOrders {
    private HashMap<Partner, Partner> orders;

    public CustomerOrders(HashMap<Partner, Partner> orders) {
        this.orders = orders;
    }

    public HashMap<Partner, Partner> getOrders() {
        return orders;
    }
}
