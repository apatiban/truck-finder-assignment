package com.tf.data;

import org.apache.lucene.util.SloppyMath;

public class DistanceCalculator {

    public static double distanceInMiles(double lat1, double long1, double lat2,
            double long2) {

        double dist = SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        // System.out.println("Meters " + dist);
        return dist * 0.000621371;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    public static double calculateDistanceInKilometer(double userLat, double userLng,
            double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                        * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // return (AVERAGE_RADIUS_OF_EARTH_KM * c) / 0.621371;
        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }

    public static void main(String[] args) {
        System.out.println("TEST");
        double distance = DistanceCalculator.distanceInMiles(37.7620192003564, -122.427306422513,
                37.8058853501009, -122.415945246637);
        System.out.println(distance);
    }

}
