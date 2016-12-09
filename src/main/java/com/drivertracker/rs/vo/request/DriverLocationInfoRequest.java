package com.drivertracker.rs.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "driverLocationInfo")
@JsonInclude(Include.NON_NULL)
public class DriverLocationInfoRequest {

    @JsonProperty("latitude")
    private Double latigude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("accuracy")
    private Float accuracy;

    public DriverLocationInfoRequest() {
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

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

}
