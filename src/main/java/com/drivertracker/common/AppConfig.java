package com.drivertracker.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${default.radius}")
    private Integer radius;

    @Value("${default.page.limit}")
    private Short pageLimit;

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Short getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Short pageLimit) {
        this.pageLimit = pageLimit;
    }

}
