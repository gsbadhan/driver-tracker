package com.drivertracker.rs.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonRootName(value = "driverSearchRow")
public class DriverSearchRow {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("latitude")
    private Double latigude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("distance")
    private String distance;

    public DriverSearchRow() {
    }

    public DriverSearchRow(Long id, Double latigude, Double longitude, String distance) {
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}
