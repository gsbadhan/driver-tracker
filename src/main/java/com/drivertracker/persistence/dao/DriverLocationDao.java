package com.drivertracker.persistence.dao;

import java.util.List;

import com.drivertracker.persistence.entity.DriverLocation;
import com.drivertracker.persistence.entity.DriverSearchResults;

public interface DriverLocationDao {
	/**
	 * save/update driver information.
	 * 
	 * @param driverLocation
	 * @return
	 */
	boolean saveOrUpdate(DriverLocation driverLocation);

	/**
	 * search drivers within given radius w.r.t. latitude and longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param start
	 * @param limit
	 * @return
	 */
	List<DriverSearchResults> search(Double latitude, Double longitude, Integer radius, Short start, Short limit);

}
