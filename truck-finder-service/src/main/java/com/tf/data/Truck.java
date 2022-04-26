package com.tf.data;

import com.tf.app.util.Utils;

public class Truck {

    // locationid Applicant FacilityType cnn LocationDescription Address blocklot

    // block lot permit Status FoodItems X Y Latitude Longitude Schedule
    // dayshours NOISent Approved Received PriorPermit ExpirationDate Location
    // Fire Prevention Districts Police Districts Supervisor Districts Zip Codes
    // Neighborhoods (old)
    /*
     * truck.setLocationId(truckDetails[0]);
     * truck.setLocationDesc(truckDetails[4]);
     * truck.setAddress(truckDetails[5]);
     * truck.setFoodItem(truckDetails[11]);
     * truck.setSchedule(truckDetails[11]);
     * truck.setDayHours(truckDetails[17]);
     * truck.setLocation(truckDetails[23]);
     * truck.setZipCode(truckDetails[27]);
     * truck.setDistance(distance);
     */
    private String locationId;
    private String locationDesc;
    private String address;
    private String foodItem;
    private String schedule;
    private String dayHours;
    private String location;
    private String zipCode;
    private Double distance;

    /*
     * private Long blocklot;
     * private Long block;
     * private Integer lot;
     * private String permit;
     * private String status;
     * private Long X;
     * private Long Y;
     * private Long latitdue;
     * private Long longitude;
     * private String noiSet;
     * private String approved;
     * private Long received;
     * private Integer priorPermit;
     * private String expiration;
     * private Integer firePreventDistricts;
     * private Integer policeDistricts;
     * private Integer supervisorDistricts;
     * private Integer neighbors;
     * private String facilityType;
     * private Long cnn;
     * private String applicant;
     */

    public String getLocationId() {
        return locationId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getZipCode() {
        return Utils.getLong(zipCode);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDayHours() {
        return dayHours;
    }

    public void setDayHours(String dayHours) {
        this.dayHours = dayHours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
