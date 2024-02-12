package org.food.delivery;

import org.food.delivery.service.distribute.Trip;
import org.food.delivery.location.GeoLocation;
import org.food.delivery.orders.CustomerOrders;
import org.food.delivery.partners.Customer;
import org.food.delivery.partners.DeliveryMan;
import org.food.delivery.partners.Partner;
import org.food.delivery.partners.Restaurant;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestFoodDelivery {

    private final static Customer customerA = new Customer("Sanjay",
            new GeoLocation(65.4333, 52.2344));

    private final static Customer customerB = new Customer("Birju",
            new GeoLocation(22.1234, 86.5342));

    private final static Restaurant restaurantA = new Restaurant("Karims",
            new GeoLocation(78.8827, 31.2121),
            10.0);

    private final static Restaurant restaurantB = new Restaurant("Biryani Point",
            new GeoLocation(24.2121, 85.1122),
            12.0);

    private final static DeliveryMan deliveryMan = new DeliveryMan("Aman",
            new GeoLocation(77.1025, 28.7041));

    HashMap<Partner, Partner> custmoreOrders = new HashMap<>();
    Set<Partner> customers = new HashSet<>();

    @Test
    public void testTrip() {
        custmoreOrders.put(customerA, restaurantA);
        custmoreOrders.put(customerB, restaurantB);

        Trip trip = new Trip(deliveryMan, new CustomerOrders(custmoreOrders));
        System.out.println(trip.getTripLocations());
        assertEquals(Arrays.asList("Aman", "Karims", "Sanjay", "Biryani Point", "Birju"), trip.getTripLocations());
    }
}
