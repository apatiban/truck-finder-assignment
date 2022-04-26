package com.tf.data;

public class Distance {

    private String locationId;
    private double distance;

    public Distance(String locationId, double distance) {
        this.locationId = locationId;
        this.distance = distance;
    }

    public String getLocationId() {
        return locationId;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Distance Object [locationId=" + locationId + ", distance=" + distance + "]";
    }

}
