package com.tf.entity;

import java.text.DecimalFormat;

import com.tf.util.Utils;

public class Truck {

    // locationid Applicant FacilityType cnn LocationDescription Address blocklot

    // block lot permit Status FoodItems X Y Latitude Longitude Schedule
    // dayshours NOISent Approved Received PriorPermit ExpirationDate Location
    // Fire Prevention Districts Police Districts Supervisor Districts Zip Codes
    // Neighborhoods (old)

    private String locationId;
    private String locationDesc;
    private String address;
    private String foodItem;
    private String schedule;
    private String dayHours;
    private String location;
    private String zipCode;
    private Double distance;
    private String distanceUnit = "M"; // Currenly hardcodoing to M (Miles) as distance gets coverted to Miles

    public String getLocationId() {
        return locationId;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Double getDistance() {
        DecimalFormat df = new DecimalFormat("#.##");
        distance = Double.valueOf(df.format(distance));

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

    @Override
    public String toString() {
        return "[address=" + address + ", dayHours=" + dayHours + ", distance=" + distance + ", foodItem="
                + foodItem + ", location=" + location + ", locationDesc=" + locationDesc + ", locationId=" + locationId
                + ", schedule=" + schedule + ", zipCode=" + zipCode + "]";
    }

}
