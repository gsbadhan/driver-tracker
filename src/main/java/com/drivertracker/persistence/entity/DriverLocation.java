package com.drivertracker.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "driverlocation", type = "driverlocation", useServerConfiguration = true, createIndex = true)
public class DriverLocation {
    @Id
    private Long driverId;

    @GeoPointField
    private GeoPoint geoPoint;

    @Field(type = FieldType.Float, index = FieldIndex.no)
    private Float accuracy;

    public DriverLocation() {
    }

    public DriverLocation(Long driverId, Double latitude, Double longitude, Float accuracy) {
        this.driverId = driverId;
        this.geoPoint = new GeoPoint(latitude, longitude);
        this.accuracy = accuracy;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((driverId == null) ? 0 : driverId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DriverLocation other = (DriverLocation) obj;
        if (driverId == null) {
            if (other.driverId != null)
                return false;
        } else if (!driverId.equals(other.driverId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DriverLocation [driverId=" + driverId + ", geoPoint=" + geoPoint.getLat() + ":" + geoPoint.getLon()
                + ", accuracy=" + accuracy + "]";
    }

}
