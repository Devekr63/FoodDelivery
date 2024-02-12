package org.food.delivery.service.distribute;

import org.food.delivery.orders.CustomerOrders;
import org.food.delivery.partners.Customer;
import org.food.delivery.partners.Partner;
import org.food.delivery.partners.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

public class Trip {
    private Partner deliveryMan;
    private CustomerOrders customerOrders;

    private List<Partner> locations;

    public Trip(Partner deliveryMan, CustomerOrders customerOrders) {
        this.deliveryMan = deliveryMan;
        this.customerOrders = customerOrders;
        createLocations();
    }

    private void createLocations() {
        locations = new ArrayList<>();
        locations.add(deliveryMan);
        customerOrders.getOrders().forEach((customer, order) -> {
            locations.addAll(Arrays.asList(customer, order));
        });
    }

    public List<String> getTripLocations() {
        List<Partner> tripRoute = new ArrayList<>();
        Set<Partner> visitedPartners = new HashSet<>();
        Set<Partner> ordersPickedFromRest = new HashSet<>();

        Partner currentLocation = deliveryMan;
        tripRoute.add(deliveryMan);
        visitedPartners.add(deliveryMan);

        for (Partner partner : locations) {
            currentLocation = findNearestNeighbor(currentLocation, visitedPartners, ordersPickedFromRest);
            if (currentLocation != null) {
                tripRoute.add(currentLocation);
            }
            visitedPartners.add(currentLocation);
        }

        var tripStream = tripRoute.stream().map(partner -> {
            return partner.getName();
        });
        return tripStream.collect(Collectors.toList());
    }

    private Partner findNearestNeighbor(Partner currentLocation, Set<Partner> visitedPartners, Set<Partner> ordersPickedFromRest) {
        Partner nearestLocation = null;
        double minDistance = Double.MAX_VALUE;

        for (Partner partner : locations) {
            if (!visitedPartners.contains(partner)) {
                double distance = currentLocation
                        .getLocation()
                        .distanceFromLocation(partner.getLocation());
                if (partner.getClass() == Restaurant.class && ((Restaurant) partner).getCookingTime() * 20 > distance) {
                    distance = ((Restaurant) partner).getCookingTime();
                }
                if (distance < minDistance) {
                    if (currentLocation == deliveryMan && partner.getClass() == Restaurant.class) {
                        minDistance = distance;
                        nearestLocation = pickOrder(partner, ordersPickedFromRest);
                    } else if (currentLocation.getClass() == Restaurant.class) {
                        //either go to i_th customer whose order is picked
                        //else j_th restaurant
                        if (partner.getClass() == Restaurant.class) {
                            minDistance = distance;
                            nearestLocation = pickOrder(partner, ordersPickedFromRest);
                        } else if (ordersPickedFromRest.contains(customerOrders.getOrders().get(partner))) {
                            minDistance = distance;
                            nearestLocation = dropOrder(partner, ordersPickedFromRest);
                        }
                    } else if (currentLocation.getClass() == Customer.class) {
                        //then go to another restaurant
                        //or go another customer whose order is picked
                        if (partner.getClass() == Restaurant.class) {
                            minDistance = distance;
                            nearestLocation = pickOrder(partner, ordersPickedFromRest);
                        } else if (ordersPickedFromRest.contains(customerOrders.getOrders().get(partner))) {
                            minDistance = distance;
                            nearestLocation = dropOrder(partner, ordersPickedFromRest);
                        }
                    }
                }
            }
        }
        return nearestLocation;
    }

    private Partner pickOrder(Partner partner, Set<Partner> ordersPickedFromRest) {
        ordersPickedFromRest.add(partner);
        return partner;
    }

    private Partner dropOrder(Partner partner, Set<Partner> ordersPickedFromRest) {
        ordersPickedFromRest.remove(partner);
        return partner;
    }

}
