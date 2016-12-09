package com.drivertracker.common;

public class Validation {

    public static boolean isValidDriverId(Long driverId) {
        if (driverId != null && driverId > 0 && driverId <= 50000)
            return true;
        return false;
    }

    public static boolean isValidLatitude(Double latigude) {
        if (latigude != null && !(latigude < -90) && !(latigude > +90))
            return true;
        return false;
    }

    public static boolean isValidLongitude(Double longitude) {
        if (longitude != null && !(longitude < -180) && !(longitude > +180))
            return true;
        return false;
    }

    public static boolean isValidRadius(Integer radius) {
        if (radius <= 0)
            return false;

        return true;
    }

    public static boolean isValidLimit(Short limit) {
        if (limit <= 0)
            return false;

        return true;
    }

}
