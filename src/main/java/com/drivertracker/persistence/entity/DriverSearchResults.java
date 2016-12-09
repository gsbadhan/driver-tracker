package com.drivertracker.persistence.entity;

public class DriverSearchResults {
    private Long id;
    private Double latigude;
    private Double longitude;
    private Double distance;

    public DriverSearchResults(Long id, Double latigude, Double longitude, Double distance) {
        super();
        this.id = id;
        this.latigude = latigude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatigude() {
        return latigude;
    }

    public void setLatigude(Double latigude) {
        this.latigude = latigude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DriverSearchResults [id=" + id + ", latigude=" + latigude + ", longitude=" + longitude + ", distance="
                + distance + "]";
    }

}
