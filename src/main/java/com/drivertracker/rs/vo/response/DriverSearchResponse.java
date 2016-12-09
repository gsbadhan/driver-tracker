package com.drivertracker.rs.vo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonRootName(value = "driverResults")
public class DriverSearchResponse {
    private List<DriverSearchRow> drivers;

    public DriverSearchResponse() {
    }

    public DriverSearchResponse(List<DriverSearchRow> drivers) {
        super();
        this.drivers = drivers;
    }

    public List<DriverSearchRow> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverSearchRow> drivers) {
        this.drivers = drivers;
    }

}
