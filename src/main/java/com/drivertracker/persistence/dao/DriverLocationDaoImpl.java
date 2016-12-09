package com.drivertracker.persistence.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.index.query.GeoDistanceRangeQueryBuilder;
import org.perf4j.aop.Profiled;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.drivertracker.common.Utils;
import com.drivertracker.messages.PerfLabels;
import com.drivertracker.persistence.entity.DriverLocation;
import com.drivertracker.persistence.entity.DriverSearchResults;

@Repository
public class DriverLocationDaoImpl implements DriverLocationDao {

    private final DriverLocationElasticRepo driverLocationRepo;

    @Inject
    public DriverLocationDaoImpl(DriverLocationElasticRepo driverLocationRepo) {
        this.driverLocationRepo = driverLocationRepo;
    }

    @Override
    @Profiled(tag = PerfLabels.DAO_SAVE_DRIVER_LOCATION, logFailuresSeparately = true)
    public boolean saveOrUpdate(DriverLocation driverLocation) {
        DriverLocation out = driverLocationRepo.save(driverLocation);
        if (out != null) {
            return true;
        }
        return false;
    }

    @Override
    @Profiled(tag = PerfLabels.DAO_SEARCH_DRIVER_LOCATION, logFailuresSeparately = true)
    public List<DriverSearchResults> search(Double latitude, Double longitude, Integer radius, Short start,
                                            Short limit) {
        List<DriverSearchResults> list = new LinkedList<>();
        String minDistance = "0m";
        GeoDistanceRangeQueryBuilder builder = new GeoDistanceRangeQueryBuilder("geoPoint");
        builder.point(latitude, longitude).gt(minDistance).from(minDistance).to(radius).optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);
        Pageable pageable = new PageRequest(start, limit);
        Iterable<DriverLocation> iterable = driverLocationRepo.search(builder, pageable);
        Iterator<DriverLocation> itr = iterable.iterator();
        while (itr.hasNext()) {
            DriverLocation dl = itr.next();
            double distance = Utils.distance(latitude, dl.getGeoPoint().getLat(), longitude, dl.getGeoPoint().getLon());
            list.add(new DriverSearchResults(dl.getDriverId(), dl.getGeoPoint().getLat(), dl.getGeoPoint().getLon(),
                    distance));
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

}
